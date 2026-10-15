package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.NoticeCommentsDTO;
import java.util.List;
/**
* @description: 资讯评论表业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface NoticeCommentsService extends IService<NoticeCommentsDTO> {

    /**
    * 默认分页方法
    * @param noticeCommentsDTO
    * @return
    */
    Page<NoticeCommentsDTO> basePage(NoticeCommentsDTO noticeCommentsDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param noticeCommentsDTO
    * @return
    */
    List<NoticeCommentsDTO> findList(NoticeCommentsDTO noticeCommentsDTO);

}
