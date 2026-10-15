package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.VideosDTO;
import java.util.List;
/**
* @description: 科普视频业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface VideosService extends IService<VideosDTO> {

    /**
    * 默认分页方法
    * @param videosDTO
    * @return
    */
    Page<VideosDTO> basePage(VideosDTO videosDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param videosDTO
    * @return
    */
    List<VideosDTO> findList(VideosDTO videosDTO);

}
