package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.AdminDTO;
import java.util.List;
/**
* @description: 管理员业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface AdminService extends IService<AdminDTO> {

    /**
    * 默认分页方法
    * @param adminDTO
    * @return
    */
    Page<AdminDTO> basePage(AdminDTO adminDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param adminDTO
    * @return
    */
    List<AdminDTO> findList(AdminDTO adminDTO);

}
