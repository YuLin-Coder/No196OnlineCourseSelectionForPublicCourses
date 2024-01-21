package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *成绩管理：(AchievementManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AchievementManagement")
public class AchievementManagement implements Serializable {

    //AchievementManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_management_id")
    private Integer achievement_management_id;
    // 课程名称
    @Basic
    private String course_name;
    // 专业类型
    @Basic
    private String professional_type;
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
    // 发布时间
    @Basic
    private Timestamp release_time;
    // 课程成绩
    @Basic
    private Integer course_achievement;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
