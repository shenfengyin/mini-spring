package org.springframework.aop.dynamic_proxy_study.cglib_proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.dynamic_proxy_study.jdk_proxy.PrintFuncImpl;

import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/15 - 22:37
 * Description:
 */
public class CGLIMethodInterceptorTest {
    public static void main(String[] args) {
        $Proxy1 proxy = new $Proxy1(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                return method.invoke(new PrintFuncImpl(), objects);
//                return methodProxy.invokeSuper(o, objects); // 内部无反射，结合代理用
//                return methodProxy.invoke(new PrintFuncImpl, objects); // 内部无反射，结合目标用
                /*
                fastClass是CGLIB中用于快速方法调用的一种优化机制。
                在CGLIB代理对象调用方法的时候，为了提高性能，
                CGLIB使用fastClass来缓存代理类的方法索引，减少方法调用时的性能开销。
                通过快速查找代理方法，可以提高代理方法调用的效率。
                 */
                // 总结：MethodProxy借用原始类的签名信息，调用的原始方法，不用反射。注意对比method.invoke
                // 无论是jdk的InvocationHandler.invoke还是cglib的MethodInterceptor.intercept
                // 内部都是用了method.invoke()反射调用，
                // jdk采用了第17次才生成methodProxy代理避免反射，而cglib从一开始就缓存了MethodProxy，以空间换时间
            }
        });

        proxy.printFunc();
        int i = proxy.secondFunc(4);
        System.out.println(i);
    }
}
