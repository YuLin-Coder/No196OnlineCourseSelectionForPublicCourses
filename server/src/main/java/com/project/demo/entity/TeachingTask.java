package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *教学任务：(TeachingTask)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TeachingTask")
public class TeachingTask implements Serializable {

    //TeachingTask编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teaching_task_id")
    private Integer teaching_task_id;
    // 任务名称
    @Basic
    private String task_name;
    // 任务时间
    @Basic
    private Timestamp task_time;
    // 指定教师
    @Basic
    private Integer designated_teacher;
    // 附件
    @Basic
    private String enclosure;
    // 任务内容
    @Basic
    private String task_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
