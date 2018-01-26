package com.github.dge1992.ssm.dynamic;

/**
 * @author  dong
 * @create  2018/1/25 10:06
 * @desc 使用ThreadLocal技术来记录当前线程中的数据源的key
 **/
public class DynamicDataSourceHolder {

    //主库对应的数据源key
    private static final  String MASTER_DATA_SOURCE = "master";

    //从库对应的数据源key
    private static final  String SLAVE_DATA_SOURCE = "slave";

    //使用ThreadLocal记录当前的数据源key
    private static final ThreadLocal<String> holder = ThreadLocal.withInitial(
            () -> DynamicDataSourceHolder.SLAVE_DATA_SOURCE
    );

    //设置数据源key
    public static void putDataSourceKey(String key){
        holder.set(key);
    }

    //获取数据源key
    public static String getDataSourceKey(){
        return holder.get();
    }

    //标记主库
    public static void markMaster(){
        putDataSourceKey(MASTER_DATA_SOURCE);
    }

    //标记从库
    public static void markSlaver(){
        putDataSourceKey(SLAVE_DATA_SOURCE);
    }
}
