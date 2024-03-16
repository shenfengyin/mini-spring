package org.springframework.aop.dynamic_proxy_study.AOP;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.dynamic_proxy_study.jdk_proxy.IPrintFunc;
import org.springframework.aop.dynamic_proxy_study.jdk_proxy.PrintFuncImpl;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author:sfy
 * @Date: 2024/3/16 - 21:37
 * Description:
 */
public class AOPTest {
    public static void main(String[] args) throws RuntimeException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        //1. 创建切点pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* printFunc())");
//        pointcut.setExpression("execution(* foo())");
        //2. 创建通知advice
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                System.out.println("before...");
                Object result = methodInvocation.proceed();
                System.out.println("after...");
                return result;
            }
        };
        //3. 合成切面advisor / aspect
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor(pointcut, advice);
        //4. 创建代理proxy
        PrintFuncImpl printFunc = new PrintFuncImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(printFunc);
        proxyFactory.addAdvisor(advisor);
        IPrintFunc proxy = (IPrintFunc) proxyFactory.getProxy();
        proxy.printFunc();
        proxy.secondFunc(5);
    }

}
