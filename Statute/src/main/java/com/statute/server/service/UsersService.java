package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.UsersDTO;
import java.util.List;
/**
* @description: 用户表业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface UsersService extends IService<UsersDTO> {

    /**
    * 默认分页方法
    * @param usersDTO
    * @return
    */
    Page<UsersDTO> basePage(UsersDTO usersDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param usersDTO
    * @return
    */
    List<UsersDTO> findList(UsersDTO usersDTO);

}
