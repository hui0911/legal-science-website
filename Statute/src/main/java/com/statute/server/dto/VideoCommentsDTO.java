package com.statute.server.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.statute.server.entity. VideoComments;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @description: 视频评论表数据传输层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@ToString
@TableName("video_comments")
public class VideoCommentsDTO extends VideoComments {



    @TableField(exist = false)
    private List<VideoCommentsDTO> videoCommentsDTOS;

    /**
    * 视频标题
    */
    @TableField(exist = false)
    private String title;
    /**
    * 封面图片
    */
    @TableField(exist = false)
    private String cover;
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
     * 回复人姓名
     */
    @TableField(exist = false)
    private String nickNameTwo;
    /**
     * 回复人头像
     */
    @TableField(exist = false)
    private String headImgTwo;



}
