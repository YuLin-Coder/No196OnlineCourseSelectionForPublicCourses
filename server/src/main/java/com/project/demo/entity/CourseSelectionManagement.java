package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *选课管理：(CourseSelectionManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CourseSelectionManagement")
public class CourseSelectionManagement implements Serializable {

    //CourseSelectionManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_selection_management_id")
    private Integer course_selection_management_id;
    // 课程编号
    @Basic
    private String course_number;
    // 课程名称
    @Basic
    private String course_name;
    // 专业类型
    @Basic
    private String professional_type;
    // 适学年级
    @Basic
    private String suitable_grade;
    // 限定人数
    @Basic
    private String limited_number_of_people;
    // 教师
    @Basic
    private Integer teacher;
    // 学生
    @Basic
    private Integer student;
    // 学号
    @Basic
    private String student_number;
    // 班号
    @Basic
    private String class_number;
    // 人数
    @Basic
    private String number_of_people;
    @Basic
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
