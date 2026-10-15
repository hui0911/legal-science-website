package com.statute.server.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.statute.server.entity. Videos;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @description: 科普视频数据传输层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@ToString
@TableName("videos")
public class VideosDTO extends Videos {

    /**
    * 分类名称
    */
    @TableField(exist = false)
    private String typeName;

}
