package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 用户表实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users extends BaseEntity {

    /**
    * 用户编号
    */
    private Long id;
    /**
    * 账号
    */
    private String account;
    /**
    * 密码
    */
    private String password;
    /**
    * 姓名
    */
    private String nickName;
    /**
    * 头像
    */
    private String headImg;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
