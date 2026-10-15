package com.statute.server.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.statute.server.entity. Banner;
import lombok.Data;
import lombok.ToString;

/**
* @description: 轮播图表数据传输层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@ToString
@TableName("banner")
public class BannerDTO extends Banner {


}
