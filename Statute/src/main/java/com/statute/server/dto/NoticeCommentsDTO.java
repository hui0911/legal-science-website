package com.statute.server.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.statute.server.entity. NoticeComments;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @description: 资讯评论表数据传输层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@ToString
@TableName("notice_comments")
public class NoticeCommentsDTO extends NoticeComments {
    @TableField(exist = false)
    private List<NoticeCommentsDTO> noticeCommentsDTOS;
    /**
    * 资讯图片
    */
    @TableField(exist = false)
    private String bannerImg;
    /**
    * 资讯标题
    */
    @TableField(exist = false)
    private String title;
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
