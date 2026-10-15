package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.NoticeDao;
import com.statute.server.dto.NoticeDTO;
import com.statute.server.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 资讯信息表业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeDTO> implements NoticeService {

    @Override
    public Page<NoticeDTO> basePage(NoticeDTO noticeDTO) {
        if (null != noticeDTO.getCurrent() && null != noticeDTO.getPageSize())
            noticeDTO.setCurrent((noticeDTO.getCurrent() - 1) * noticeDTO.getPageSize());
        List<NoticeDTO> list = this.baseMapper.findList(noticeDTO);
        int count = this.baseMapper.findPageCount(noticeDTO);
        Page<NoticeDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<NoticeDTO> findList(NoticeDTO noticeDTO) {
        return this.baseMapper.findList(noticeDTO);
    }

}
