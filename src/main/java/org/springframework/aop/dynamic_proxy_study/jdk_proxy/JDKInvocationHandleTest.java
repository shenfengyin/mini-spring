package org.springframework.aop.dynamic_proxy_study.jdk_proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/15 - 18:56
 * Description:
 */
public class JDKInvocationHandleTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        $Proxy0 proxy = new $Proxy0(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                // 1 功能增强
                System.out.println("before...");
                // 2 调用方法
//                new PrintFuncImpl().printFunc();
                return method.invoke(new PrintFuncImpl(), args);
            }
        });
        proxy.printFunc();
        int i = proxy.secondFunc(3);
        System.out.println(i);
    }
}
