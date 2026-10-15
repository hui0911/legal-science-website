package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.SubscribesDTO;
import java.util.List;
/**
* @description: 预约信息业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface SubscribesService extends IService<SubscribesDTO> {

    /**
    * 默认分页方法
    * @param subscribesDTO
    * @return
    */
    Page<SubscribesDTO> basePage(SubscribesDTO subscribesDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param subscribesDTO
    * @return
    */
    List<SubscribesDTO> findList(SubscribesDTO subscribesDTO);

}
