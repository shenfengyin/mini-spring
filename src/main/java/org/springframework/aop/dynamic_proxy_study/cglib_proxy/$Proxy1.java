package org.springframework.aop.dynamic_proxy_study.cglib_proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.dynamic_proxy_study.jdk_proxy.PrintFuncImpl;

import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/15 - 20:39
 * Description:
 */
public class $Proxy1 extends PrintFuncImpl { //这里直接是子类继承，无需接口
    static Method m0;
    static Method m1;
    static MethodProxy m0Proxy;
    static MethodProxy m1Proxy;
    static {
        try {
            m0 = PrintFuncImpl.class.getMethod("printFunc");
            m1 = PrintFuncImpl.class.getMethod("secondFunc", int.class);
            m0Proxy = MethodProxy.create(PrintFuncImpl.class, $Proxy1.class, "()V", "printFunc", "printFuncSuper");
            m1Proxy = MethodProxy.create(PrintFuncImpl.class, $Proxy1.class, "(I)I", "secondFunc", "secondFuncSuper");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    private MethodInterceptor methodInterceptor;

    public $Proxy1() {}
    public $Proxy1(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }


    // >>>>>> 带增强功能的方法
    @Override
    public void printFunc() {
        try {
            methodInterceptor.intercept(this, m0, new Object[0], m0Proxy);
//            methodInterceptor.intercept(this, m0, new Object[0], null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int secondFunc(int num) {
        try {
            Object result = methodInterceptor.intercept(this, m1, new Object[]{num}, m1Proxy);
            return (int) result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    // >>>>>>>带原始功能的方法
    public void printFuncSuper() {
        super.printFunc();
    }
    public int secondFuncSuper(int num) {
        return super.secondFunc(num);
    }


}
