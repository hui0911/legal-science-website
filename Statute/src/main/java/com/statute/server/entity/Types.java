package com.statute.server.entity;

import com.statute.server.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 分类信息实体类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Types extends BaseEntity {

    /**
    * 类型编号
    */
    private Long id;
    /**
    * 分类名称
    */
    private String typeName;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
