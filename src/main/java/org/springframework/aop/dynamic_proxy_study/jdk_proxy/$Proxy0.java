package org.springframework.aop.dynamic_proxy_study.jdk_proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Author:sfy
 * @Date: 2024/3/15 - 18:59
 * Description: 代理方法为了自动生成的通用性，抽象了InvocationHandler方便业务自定义增加动态代理的逻辑
 * Proxy对于具体业务逻辑，完全无感知。
 * 业务层proxy.method1(args1) -> Proxy.h.invoke(this, method1, args) -> new InvocationHandle(...)
 */
public class $Proxy0 implements org.springframework.aop.dynamic_proxy_study.jdk_proxy.IPrintFunc {
    private InvocationHandler h;

    public $Proxy0(InvocationHandler invocationHandler) {
        this.h = invocationHandler;
    }

    // 这里是动态生成代理类，不能写死逻辑，可以把这块逻辑抽离出来。。。
//    public void printFunc() {
//        // 1 功能增强
//        System.out.println("before...");
//        // 2 调用方法
//        new PrintFuncImpl().printFunc();
//    }

    @Override
    public void printFunc() {
        try {
//            Method method = IPrintFunc.class.getMethod("printFunc");
            this.h.invoke(this, printFunc, new Object[0]); //这里把具体的实现交给了invocationHandler
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int secondFunc(int num) {
        try {
            Method method = org.springframework.aop.dynamic_proxy_study.jdk_proxy.IPrintFunc.class.getMethod("secondFunc", int.class);
            Object result = this.h.invoke(this, method, new Object[]{num});
            return (int) result;
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    // 这里起到缓存的作用，不至于每一次调用都反射一次（毕竟反射低效）
    static Method printFunc;
    static Method secondFunc;
    static {
        try {
            printFunc = org.springframework.aop.dynamic_proxy_study.jdk_proxy.IPrintFunc.class.getMethod("printFunc");
            secondFunc = org.springframework.aop.dynamic_proxy_study.jdk_proxy.IPrintFunc.class.getMethod("secondFunc", int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
