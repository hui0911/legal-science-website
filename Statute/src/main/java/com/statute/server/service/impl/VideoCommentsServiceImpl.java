package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.VideoCommentsDao;
import com.statute.server.dto.VideoCommentsDTO;
import com.statute.server.service.VideoCommentsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 视频评论表业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("videoCommentsService")
public class VideoCommentsServiceImpl extends ServiceImpl<VideoCommentsDao, VideoCommentsDTO> implements VideoCommentsService {

    @Override
    public Page<VideoCommentsDTO> basePage(VideoCommentsDTO videoCommentsDTO) {
        if (null != videoCommentsDTO.getCurrent() && null != videoCommentsDTO.getPageSize())
            videoCommentsDTO.setCurrent((videoCommentsDTO.getCurrent() - 1) * videoCommentsDTO.getPageSize());
        List<VideoCommentsDTO> list = this.baseMapper.findList(videoCommentsDTO);
        int count = this.baseMapper.findPageCount(videoCommentsDTO);
        Page<VideoCommentsDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<VideoCommentsDTO> findList(VideoCommentsDTO videoCommentsDTO) {
        return this.baseMapper.findList(videoCommentsDTO);
    }

}
