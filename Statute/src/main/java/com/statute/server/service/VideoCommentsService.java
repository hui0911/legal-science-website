package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.VideoCommentsDTO;
import java.util.List;
/**
* @description: 视频评论表业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface VideoCommentsService extends IService<VideoCommentsDTO> {

    /**
    * 默认分页方法
    * @param videoCommentsDTO
    * @return
    */
    Page<VideoCommentsDTO> basePage(VideoCommentsDTO videoCommentsDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param videoCommentsDTO
    * @return
    */
    List<VideoCommentsDTO> findList(VideoCommentsDTO videoCommentsDTO);

}
