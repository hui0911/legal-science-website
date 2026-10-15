package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 资讯信息表实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice extends BaseEntity {

    /**
    * 资讯编号
    */
    private Long id;
    /**
    * 资讯图片
    */
    private String bannerImg;
    /**
    * 所属分类
    */
    private Long typeId;
    /**
    * 资讯内容
    */
    private String content;
    /**
    * 资讯简介
    */
    private String info;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 资讯标题
    */
    private String title;
    /**
    * 阅读量
    */
    private Integer seeNum;


}
