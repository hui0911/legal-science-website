package com.statute.server.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.statute.server.entity. Admin;
import lombok.Data;
import lombok.ToString;

/**
* @description: 管理员数据传输层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@ToString
@TableName("admin")
public class AdminDTO extends Admin {


}
