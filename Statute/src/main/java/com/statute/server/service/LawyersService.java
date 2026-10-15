package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.LawyersDTO;
import java.util.List;
/**
* @description: 律师信息业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface LawyersService extends IService<LawyersDTO> {

    /**
    * 默认分页方法
    * @param lawyersDTO
    * @return
    */
    Page<LawyersDTO> basePage(LawyersDTO lawyersDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param lawyersDTO
    * @return
    */
    List<LawyersDTO> findList(LawyersDTO lawyersDTO);

}
