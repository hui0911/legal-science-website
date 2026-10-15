package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.NoticeCommentsDao;
import com.statute.server.dto.NoticeCommentsDTO;
import com.statute.server.service.NoticeCommentsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 资讯评论表业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("noticeCommentsService")
public class NoticeCommentsServiceImpl extends ServiceImpl<NoticeCommentsDao, NoticeCommentsDTO> implements NoticeCommentsService {

    @Override
    public Page<NoticeCommentsDTO> basePage(NoticeCommentsDTO noticeCommentsDTO) {
        if (null != noticeCommentsDTO.getCurrent() && null != noticeCommentsDTO.getPageSize())
            noticeCommentsDTO.setCurrent((noticeCommentsDTO.getCurrent() - 1) * noticeCommentsDTO.getPageSize());
        List<NoticeCommentsDTO> list = this.baseMapper.findList(noticeCommentsDTO);
        int count = this.baseMapper.findPageCount(noticeCommentsDTO);
        Page<NoticeCommentsDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<NoticeCommentsDTO> findList(NoticeCommentsDTO noticeCommentsDTO) {
        return this.baseMapper.findList(noticeCommentsDTO);
    }

}
