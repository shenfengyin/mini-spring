package org.springframework.aop.dynamic_proxy_study.z_ignore.Service;

import org.springframework.aop.dynamic_proxy_study.z_ignore.POJO.Student;

/**
 * @Author:sfy
 * @Date: 2024/3/14 - 21:25
 * Description:
 */
public class StudentServiceImpl implements IStudentService {
    public void save() {
        System.out.println("保存学生信息");
    }

    public Student query(Long id) {
        Student student = new Student();
        student.setName("sy");
        student.setAge(18);
        return student;
    }
}
