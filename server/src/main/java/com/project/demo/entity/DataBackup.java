package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *数据备份：(DataBackup)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DataBackup")
public class DataBackup implements Serializable {

    //DataBackup编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_backup_id")
    private Integer data_backup_id;
    // 备份时间
    @Basic
    private Timestamp backup_time;
    // 备份名称
    @Basic
    private String backup_name;
    // 备份文件
    @Basic
    private String backup_files;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
