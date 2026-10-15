package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.LawyersDao;
import com.statute.server.dto.LawyersDTO;
import com.statute.server.service.LawyersService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 律师信息业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("lawyersService")
public class LawyersServiceImpl extends ServiceImpl<LawyersDao, LawyersDTO> implements LawyersService {

    @Override
    public Page<LawyersDTO> basePage(LawyersDTO lawyersDTO) {
        if (null != lawyersDTO.getCurrent() && null != lawyersDTO.getPageSize())
            lawyersDTO.setCurrent((lawyersDTO.getCurrent() - 1) * lawyersDTO.getPageSize());
        List<LawyersDTO> list = this.baseMapper.findList(lawyersDTO);
        int count = this.baseMapper.findPageCount(lawyersDTO);
        Page<LawyersDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<LawyersDTO> findList(LawyersDTO lawyersDTO) {
        return this.baseMapper.findList(lawyersDTO);
    }

}
