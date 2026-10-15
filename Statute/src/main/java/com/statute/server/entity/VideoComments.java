package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 视频评论表实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoComments extends BaseEntity {

    /**
    * 评论编号
    */
    private Long id;
    /**
    * 评价内容
    */
    private String commentInfo;
    /**
    * 评论用户
    */
    private Long userId;
    /**
    * 所属视频
    */
    private Long videoId;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 回复人编号
    */
    private Long toUserId;
    /**
    * 父级评论编号
    */
    private Long parentId;


}
