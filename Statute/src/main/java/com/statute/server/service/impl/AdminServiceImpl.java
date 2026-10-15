package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.AdminDao;
import com.statute.server.dto.AdminDTO;
import com.statute.server.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 管理员业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminDTO> implements AdminService {

    @Override
    public Page<AdminDTO> basePage(AdminDTO adminDTO) {
        if (null != adminDTO.getCurrent() && null != adminDTO.getPageSize())
            adminDTO.setCurrent((adminDTO.getCurrent() - 1) * adminDTO.getPageSize());
        List<AdminDTO> list = this.baseMapper.findList(adminDTO);
        int count = this.baseMapper.findPageCount(adminDTO);
        Page<AdminDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<AdminDTO> findList(AdminDTO adminDTO) {
        return this.baseMapper.findList(adminDTO);
    }

}
