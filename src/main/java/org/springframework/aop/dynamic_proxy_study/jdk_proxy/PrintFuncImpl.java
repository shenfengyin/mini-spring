package org.springframework.aop.dynamic_proxy_study.jdk_proxy;

public class PrintFuncImpl implements IPrintFunc {
    @Override
    public void printFunc() {
        System.out.println("printFunction has done.");
    }

    @Override
    public int secondFunc(int num) {
        System.out.println("secondFunction has done, and the num is " + num);
        return num * 10;
    }
}