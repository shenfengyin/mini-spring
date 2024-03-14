package org.springframework.aop.dynamic_proxy_study.Advisor;

/**
 * @Author:sfy
 * @Date: 2024/3/14 - 21:40
 * Description:
 */
public class DaoTransaction {
    public void before(){
        System.out.println("开启事务操作");
    }

    public void after(){
        System.out.println("关闭事务");
    }
}
