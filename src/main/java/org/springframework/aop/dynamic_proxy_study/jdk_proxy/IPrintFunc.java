package org.springframework.aop.dynamic_proxy_study.jdk_proxy;

import java.lang.reflect.InvocationTargetException;

public interface IPrintFunc {
        void printFunc() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
        int secondFunc(int num) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    }