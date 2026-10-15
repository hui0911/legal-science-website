package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.TypesDTO;
import java.util.List;
/**
* @description: 分类信息业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface TypesService extends IService<TypesDTO> {

    /**
    * 默认分页方法
    * @param typesDTO
    * @return
    */
    Page<TypesDTO> basePage(TypesDTO typesDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param typesDTO
    * @return
    */
    List<TypesDTO> findList(TypesDTO typesDTO);

}
