package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学院管理：(CollegeManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CollegeManagement")
public class CollegeManagement implements Serializable {

    //CollegeManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_management_id")
    private Integer college_management_id;
    // 学院
    @Basic
    private String college;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
