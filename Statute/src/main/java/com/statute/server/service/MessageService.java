package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.MessageDTO;
import java.util.List;
/**
* @description: 留言信息业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface MessageService extends IService<MessageDTO> {

    /**
    * 默认分页方法
    * @param messageDTO
    * @return
    */
    Page<MessageDTO> basePage(MessageDTO messageDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param messageDTO
    * @return
    */
    List<MessageDTO> findList(MessageDTO messageDTO);

}
