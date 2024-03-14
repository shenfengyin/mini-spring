package org.springframework.aop.dynamic_proxy_study.test;

import org.junit.Test;
import org.springframework.aop.dynamic_proxy_study.Advisor.DaoTransaction;
import org.springframework.aop.dynamic_proxy_study.POJO.Student;
import org.springframework.aop.dynamic_proxy_study.Service.IStudentService;
import org.springframework.aop.dynamic_proxy_study.Service.StudentServiceImpl;
import org.springframework.aop.dynamic_proxy_study.Service.TransactionHandler;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @Author:sfy
 * @Date: 2024/3/14 - 22:08
 * Description:
 */
public class DynamicProxyTest {
    @Test
    public void testSave(){
        //增强类对象
        DaoTransaction transaction = new DaoTransaction();
        //目标执行类
        IStudentService service = new StudentServiceImpl();
        //方法拦截处理器
        TransactionHandler handler = new TransactionHandler(transaction, service);
        //获取代理实例对象
        IStudentService proxyStudentService =(IStudentService) Proxy.newProxyInstance(StudentServiceImpl.class.getClassLoader(),
                StudentServiceImpl.class.getInterfaces(), handler);
        proxyStudentService.save();
        Student query = proxyStudentService.query(1L);
        System.out.println(query);
        saveProxyClass("E:\\project\\mini-spring\\src\\main\\java\\org\\springframework\\aop\\dynamic_proxy_study");
    }


    private void saveProxyClass(String path){
        byte[] $proxy1s = ProxyGenerator.generateProxyClass("$Proxy1",
                StudentServiceImpl.class.getInterfaces());
        FileOutputStream out = null;
        try {
            out =  new FileOutputStream(new File(path + "$Proxy1.class"));
            out.write($proxy1s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(out !=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
