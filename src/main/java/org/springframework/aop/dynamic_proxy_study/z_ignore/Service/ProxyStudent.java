package org.springframework.aop.dynamic_proxy_study.z_ignore.Service;

import org.springframework.aop.dynamic_proxy_study.z_ignore.Advisor.DaoTransaction;
import org.springframework.aop.dynamic_proxy_study.z_ignore.POJO.Student;

public class ProxyStudent implements IStudentService {
    //目标类对象
    private StudentServiceImpl studentService;
    //需要做的增强对象
    private DaoTransaction transaction;
    //通过构造器获取目标类和增强类对象
    public ProxyStudent(StudentServiceImpl studentService,DaoTransaction daoTransaction){
        this.studentService = studentService;
        this.transaction = daoTransaction;
    }
        

    public void save() {
        //开启事务操作
        transaction.before();
        //目标类的操作
        studentService.save();
        //关闭事务操作
        transaction.after();
    }

    public Student query(Long id) {
        return studentService.query(id);
    }
}