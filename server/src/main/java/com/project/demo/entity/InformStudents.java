package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *通知学生：(InformStudents)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "InformStudents")
public class InformStudents implements Serializable {

    //InformStudents编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inform_students_id")
    private Integer inform_students_id;
    // 标题
    @Basic
    private String title;
    // 学生
    @Basic
    private Integer student;
    // 视频文件
    @Basic
    private String video_file_;
    // 附件
    @Basic
    private String enclosure;
    // 通知内容
    @Basic
    private String notice_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
