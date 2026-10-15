package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.NoticeDTO;
import java.util.List;
/**
* @description: 资讯信息表业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface NoticeService extends IService<NoticeDTO> {

    /**
    * 默认分页方法
    * @param noticeDTO
    * @return
    */
    Page<NoticeDTO> basePage(NoticeDTO noticeDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param noticeDTO
    * @return
    */
    List<NoticeDTO> findList(NoticeDTO noticeDTO);

}
