package org.springframework.aop.dynamic_proxy_study.jdk_proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/15 - 19:04
 * Description:
 */
interface InvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
