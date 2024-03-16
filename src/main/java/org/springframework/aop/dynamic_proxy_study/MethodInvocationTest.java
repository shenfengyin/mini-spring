package org.springframework.aop.dynamic_proxy_study;

import com.sun.tools.javac.util.List;
import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/15 - 16:18
 * Description: MethodInvocation + MethodInterceptor 实现责任链模式
 */
public class MethodInvocationTest {
    static class Target {
        public void foo() {
            System.out.println("Target.foo()...");
        }
    }

    static class Advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            System.out.println("Advice1.before()...");
            Object result = methodInvocation.proceed();//调用下一个通知或者目标
            System.out.println("Advice2.after()...");
            return result;
        }
    }
    static class Advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            System.out.println("Advice2.before()...");
            Object result = methodInvocation.proceed();//调用下一个通知或者目标
            System.out.println("Advice2.after()...");
            return result;
        }
    }

    // 负责调用多个通知、以及目标
    static class MyInvocation implements MethodInvocation {
        private Object target;
        private Method method;
        private Object[] args;
        List<MethodInterceptor> methodInterceptorList;

        private int count = 1; //调用次数

        public MyInvocation(Object target, Method method, Object[] args, List<MethodInterceptor> methodInterceptorList) {
            this.target = target;
            this.method = method;
            this.args = args;
            this.methodInterceptorList = methodInterceptorList;
        }

        @Override
        public Method getMethod() {
            return method;
        }

        @Override
        public Object[] getArguments() {
            return args;
        }

        // 调用每一个环绕通知，调用目标 （递归
        @Override
        public Object proceed() throws Throwable {
            if (count > methodInterceptorList.size()) {
                // 所有通知调用完毕，开始调用目标，返回并结束递归
                return method.invoke(target, args);
            }

            // 逐一调用通知，count++
            MethodInterceptor methodInterceptor = methodInterceptorList.get(count++ - 1);
            return methodInterceptor.invoke(this);
        }

        @Override
        public Object getThis() {
            return target;
        }

        // todo
        @Override
        public AccessibleObject getStaticPart() {
            return method;
        }


        public static void main(String[] args) throws Throwable {
            Target target = new Target();
            List<MethodInterceptor> adviceList = List.of(
                    new Advice1(),
                    new Advice2()
            );
            MyInvocation myInvocation = new MyInvocation(target, target.getClass().getMethod("foo"),
                    new Object[0], adviceList);
            myInvocation.proceed();
        }
    }
}
