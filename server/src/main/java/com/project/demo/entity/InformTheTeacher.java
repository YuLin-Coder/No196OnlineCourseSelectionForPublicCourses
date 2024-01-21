package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *通知教师：(InformTheTeacher)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "InformTheTeacher")
public class InformTheTeacher implements Serializable {

    //InformTheTeacher编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inform_the_teacher_id")
    private Integer inform_the_teacher_id;
    // 标题
    @Basic
    private String title;
    // 教师
    @Basic
    private Integer teacher;
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
