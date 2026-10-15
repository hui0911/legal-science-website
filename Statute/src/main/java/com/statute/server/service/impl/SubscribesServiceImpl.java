package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.SubscribesDao;
import com.statute.server.dto.SubscribesDTO;
import com.statute.server.service.SubscribesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 预约信息业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("subscribesService")
public class SubscribesServiceImpl extends ServiceImpl<SubscribesDao, SubscribesDTO> implements SubscribesService {

    @Override
    public Page<SubscribesDTO> basePage(SubscribesDTO subscribesDTO) {
        if (null != subscribesDTO.getCurrent() && null != subscribesDTO.getPageSize())
            subscribesDTO.setCurrent((subscribesDTO.getCurrent() - 1) * subscribesDTO.getPageSize());
        List<SubscribesDTO> list = this.baseMapper.findList(subscribesDTO);
        int count = this.baseMapper.findPageCount(subscribesDTO);
        Page<SubscribesDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<SubscribesDTO> findList(SubscribesDTO subscribesDTO) {
        return this.baseMapper.findList(subscribesDTO);
    }

}
