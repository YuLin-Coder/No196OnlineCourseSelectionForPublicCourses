package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *专业分类：(ProfessionalClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ProfessionalClassification")
public class ProfessionalClassification implements Serializable {

    //ProfessionalClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professional_classification_id")
    private Integer professional_classification_id;
    // 专业类型
    @Basic
    private String professional_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
