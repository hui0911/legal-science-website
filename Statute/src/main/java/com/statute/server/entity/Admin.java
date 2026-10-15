package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 管理员实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin extends BaseEntity {

    /**
    * 管理员编号
    */
    private Long id;
    /**
    * 姓名
    */
    private String nickName;
    /**
    * 头像
    */
    private String headImg;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 密码
    */
    private String passWord;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
