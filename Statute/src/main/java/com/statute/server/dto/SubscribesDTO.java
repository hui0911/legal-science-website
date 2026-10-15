package com.statute.server.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.statute.server.entity. Subscribes;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @description: 预约信息数据传输层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@ToString
@TableName("subscribes")
public class SubscribesDTO extends Subscribes {

    /**
    * 姓名
    */
    @TableField(exist = false)
    private String nickName;
    /**
    * 头像
    */
    @TableField(exist = false)
    private String headImg;
    /**
    * 律师名称
    */
    @TableField(exist = false)
    private String name;
    /**
    * 律师头像
    */
    @TableField(exist = false)
    private String avatar;

}
