package com.github.elliot.xa.demo;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.mysql.cj.jdbc.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;

/**
 * XADemo
 */
public class XADemo {

    final static String url1 = "jdbc:mysql://localhost:3306/elliot?serverTimezone=UTC&characterEncoding=utf-8";
    final static String url2 = "jdbc:mysql://localhost:3306/bruce?serverTimezone=UTC&characterEncoding=utf-8";


    public static void main(String[] args) throws Exception {
        // 数据库资源1
        MysqlXADataSource mysqlXADataSource = getDatasource(url1, "root", "dge_1992@163.com");
        XAConnection xaConnection = mysqlXADataSource.getXAConnection();
        Connection connection = xaConnection.getConnection();
        XAResource xaResource = xaConnection.getXAResource();
        Statement statement = connection.createStatement();

        // 数据库资源2
        MysqlXADataSource mysqlXADataSource2 = getDatasource(url2, "root", "dge_1992@163.com");
        XAConnection xaConnection2 = mysqlXADataSource2.getXAConnection();
        Connection connection2 = xaConnection2.getConnection();
        XAResource xaResource2 = xaConnection2.getXAResource();
        Statement statement2 = connection2.createStatement();

        // 创建事务分之的XID
        Xid xid1 = new MysqlXid(new byte[]{0X01}, new byte[]{0X02},100);
        Xid xid2 = new MysqlXid(new byte[]{0X011},new byte[]{0X012},100);

        String sql1 = "UPDATE points set points = points - 5 where id = '1801497529808982016'";
        executeSql(xaResource, statement, xid1, sql1);

        String sql2 = "update account set amount = amount - 5 where id = 1;";
        executeSql(xaResource2, statement2, xid2, sql2);

        // 二阶段 - 第一阶段
        int result1 = xaResource.prepare(xid1);
        int result2 = xaResource2.prepare(xid2);

        // 二阶段 - 第二阶段
        if(XAResource.XA_OK == result1 && XAResource.XA_OK == result2){
            xaResource.commit(xid1, false);
            xaResource2.commit(xid2, false);
        }else {
            xaResource.rollback(xid1);
            xaResource2.rollback(xid2);
        }
    }

    private static int executeSql(XAResource xaResource, Statement statement, Xid xid, String sql) throws Exception {
        xaResource.start(xid, XAResource.TMNOFLAGS);
        int updateCount = statement.executeUpdate(sql);
        xaResource.end(xid, XAResource.TMSUCCESS);
        return updateCount;
    }

    private static MysqlXADataSource getDatasource(String url, String user, String password){
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(url);
        mysqlXADataSource.setUser(user);
        mysqlXADataSource.setPassword(password);
        return mysqlXADataSource;
    }
}
