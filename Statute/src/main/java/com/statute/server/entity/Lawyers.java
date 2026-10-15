package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 律师信息实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lawyers extends BaseEntity {

    /**
    * 律师编号
    */
    private Long id;
    /**
    * 律师名称
    */
    private String name;
    /**
    * 律师头像
    */
    private String avatar;
    /**
    * 邮箱
    */
    private String email;
    private String password;
    /**
    * 律师等级
    */
    private Integer level;
    /**
    * 简历
    */
    private String info;
    /**
    * 证书
    */
    private String certificate;
    /**
    * 年龄
    */
    private Integer age;
    /**
    * 性别
    */
    private Integer sex;
    /**
    * 状态
    */
    private Integer status;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
