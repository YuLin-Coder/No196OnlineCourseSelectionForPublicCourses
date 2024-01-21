package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生：(Student)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Student")
public class Student implements Serializable {

    //Student编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer student_id;
    // 学号
    @Basic
    private String student_number;
    // 性别
    @Basic
    private String gender;
    // 班号
    @Basic
    private String class_number;
    // 学院
    @Basic
    private String college;
    // 专业
    @Basic
    private String major;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
