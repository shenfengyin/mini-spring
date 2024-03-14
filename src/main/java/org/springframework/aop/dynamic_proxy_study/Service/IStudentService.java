package org.springframework.aop.dynamic_proxy_study.Service;

import org.springframework.aop.dynamic_proxy_study.POJO.Student;

/**
 * @Author:sfy
 * @Date: 2024/3/14 - 21:15
 * Description:
 */
public interface IStudentService {
    /**
     * 添加学生
     */
    void save();

    /**
     * 查询学生信息
     * @param id
     * @return
     */
    Student query(Long id);
}

