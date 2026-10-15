package com.statute.server.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.statute.server.entity. Users;
import lombok.Data;
import lombok.ToString;

/**
* @description: 用户表数据传输层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Data
@ToString
@TableName("users")
public class UsersDTO extends Users {


}
