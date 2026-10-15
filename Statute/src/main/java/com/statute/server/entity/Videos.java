package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 科普视频实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Videos extends BaseEntity {

    /**
    * 视频编号
    */
    private Long id;
    /**
    * 视频标题
    */
    private String title;
    /**
    * 封面图片
    */
    private String cover;
    /**
    * 所属分类
    */
    private Long typeId;
    /**
    * 阅读量
    */
    private Integer seeNum;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 视频地址
    */
    private String videoUrl;


}
