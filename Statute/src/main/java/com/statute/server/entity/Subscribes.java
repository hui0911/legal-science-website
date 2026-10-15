package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 预约信息实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subscribes extends BaseEntity {

    /**
    * 预约编号
    */
    private Long id;
    /**
    * 预约用户
    */
    private Long userId;
    /**
    * 所属律师
    */
    private Long lawyerId;
    /**
    * 预约时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date subscribeTime;
    /**
    * 审核状态
    */
    private Integer status;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
