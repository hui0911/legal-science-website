package com.statute.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.statute.server.dto.BannerDTO;
import java.util.List;
/**
* @description: 轮播图表业务层接口
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
public interface BannerService extends IService<BannerDTO> {

    /**
    * 默认分页方法
    * @param bannerDTO
    * @return
    */
    Page<BannerDTO> basePage(BannerDTO bannerDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param bannerDTO
    * @return
    */
    List<BannerDTO> findList(BannerDTO bannerDTO);

}
