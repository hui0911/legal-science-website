package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.TypesDao;
import com.statute.server.dto.TypesDTO;
import com.statute.server.service.TypesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 分类信息业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("typesService")
public class TypesServiceImpl extends ServiceImpl<TypesDao, TypesDTO> implements TypesService {

    @Override
    public Page<TypesDTO> basePage(TypesDTO typesDTO) {
        if (null != typesDTO.getCurrent() && null != typesDTO.getPageSize())
            typesDTO.setCurrent((typesDTO.getCurrent() - 1) * typesDTO.getPageSize());
        List<TypesDTO> list = this.baseMapper.findList(typesDTO);
        int count = this.baseMapper.findPageCount(typesDTO);
        Page<TypesDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<TypesDTO> findList(TypesDTO typesDTO) {
        return this.baseMapper.findList(typesDTO);
    }

}
