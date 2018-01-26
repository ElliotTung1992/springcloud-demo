package com.github.dge1992.ssm.aop;

import com.github.dge1992.ssm.dynamic.DynamicDataSourceHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  dong
 * @create  2018/1/26 10:31
 * @desc 定义切面
 **/
@Aspect
@Component
public class DataSourceAspect {

    private static final int TX_METHOD_TIMEOUT = 5;
    private static final String AOP_POINTCUT_EXPRESSION = "execution(public * com.github.dge1992.ssm.service.*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * @author  dong
     * @create  2018/1/25 15:48
     * @desc 定义切面
     **/
    @Pointcut(AOP_POINTCUT_EXPRESSION)
    public void dynamicDataSource(){
        System.out.println("dynamicDataSource");
    }

    /**
     * @author  dong
     * @create  2018/1/25 15:45
     * @desc 定义事务策略
     **/
    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
         /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        source.setNameMap( txMap );
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    /**
     * @author  dong
     * @create  2018/1/25 15:47
     * @desc 应用事务策略到切面
     **/
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    /**
     * @author  dong
     * @create  2018/1/26 10:29
     * @desc 进入切面方法之前执行
     **/
    @Before("dynamicDataSource()")
    public void doBefore(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        if(isSlave(methodName)){
            DynamicDataSourceHolder.markSlaver();
        }else {
            DynamicDataSourceHolder.markMaster();
        }
    }

    /**
     * @author  dong
     * @create  2018/1/25 16:06
     * @desc 判断是否走从库
     **/
    private boolean isSlave(String methodName) {
        return StringUtils.startsWithAny(methodName, "query", "find", "get");
    }
}
