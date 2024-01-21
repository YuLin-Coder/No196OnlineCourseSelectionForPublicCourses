package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *课程：(Curriculum)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Curriculum")
public class Curriculum implements Serializable {

    //Curriculum编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private Integer curriculum_id;
    // 课程编号
    @Basic
    private String course_number;
    // 课程名称
    @Basic
    private String course_name;
    // 专业类型
    @Basic
    private String professional_type;
    // 课程封面
    @Basic
    private String course_cover;
    // 适学年级
    @Basic
    private String suitable_grade;
    // 教师
    @Basic
    private Integer teacher;
    // 限定人数
    @Basic
    private Integer limited_number_of_people;
    // 已选人数
    @Basic
    private Integer number_of_people_selected;
    // 课程详情
    @Basic
    private String course_details_;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;
	// 限制次数
	@Basic
	private Integer limit_times;

    // 限制次数类型
    @Basic
    private Integer limit_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

    // 上课时间
    @Basic
    private String sk_time;

    // 上课地点
    @Basic
    private String sk_address;

    // 学时
    @Basic
    private Integer xueshi;

    // 学分
    @Basic
    private Integer score;

}
