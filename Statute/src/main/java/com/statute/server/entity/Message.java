package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 留言信息实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message extends BaseEntity {

    /**
    * 留言编号
    */
    private Long id;
    /**
    * 留言用户
    */
    private Long userId;
    /**
    * 律师编号
    */
    private Long lawyerId;
    /**
    * 留言内容
    */
    private String content;
    /**
    * 回复内容
    */
    private String answer;
    /**
    * 留言时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 回复时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
