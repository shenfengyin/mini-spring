package org.springframework.aop.dynamic_proxy_study.Service;

import org.springframework.aop.dynamic_proxy_study.Advisor.DaoTransaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {
    //增强类对象
    private DaoTransaction transaction;
    //需要代理的目标对象
    private Object obj;
    
    public TransactionHandler(DaoTransaction transaction,Object obj){
        this.transaction=transaction;
        this.obj=obj;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;
        
        //判断当前方法是否是save,是才做事务操作
        if("save".equals(method.getName())){
            transaction.before();
            ret = method.invoke(obj,args);
            transaction.after();
        }else{
            ret = method.invoke(obj,args);
        }
            
        
        return ret;
    }
}