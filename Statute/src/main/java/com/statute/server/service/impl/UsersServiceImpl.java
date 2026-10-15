package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.UsersDao;
import com.statute.server.dto.UsersDTO;
import com.statute.server.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 用户表业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersDTO> implements UsersService {

    @Override
    public Page<UsersDTO> basePage(UsersDTO usersDTO) {
        if (null != usersDTO.getCurrent() && null != usersDTO.getPageSize())
            usersDTO.setCurrent((usersDTO.getCurrent() - 1) * usersDTO.getPageSize());
        List<UsersDTO> list = this.baseMapper.findList(usersDTO);
        int count = this.baseMapper.findPageCount(usersDTO);
        Page<UsersDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<UsersDTO> findList(UsersDTO usersDTO) {
        return this.baseMapper.findList(usersDTO);
    }

}
