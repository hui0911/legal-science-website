package com.statute.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.statute.server.common.BaseController;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.NoticeDTO;
import com.statute.server.dto.VideosDTO;
import com.statute.server.service.VideosService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.statute.server.common.BaseSelectEntity;

import java.util.ArrayList;

import com.statute.server.service.TypesService;
import com.statute.server.dto.TypesDTO;

import javax.annotation.Resource;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.statute.server.common.BaseSelectEntity;
import cn.hutool.core.collection.CollectionUtil;

import java.util.Date;

/**
 * @description: 科普视频对外接口层
 * @author: liwenhui
 * @create: 2026.04.07 14:09:31
 */
@RestController
@RequestMapping("/videos")
public class VideosController extends BaseController<VideosService, VideosDTO> {

    @Autowired
    private TypesService typesService;

    @Override
    @Resource(name = "videosService")
    protected void setBaseService(VideosService videosService) {
        this.baseService = videosService;
    }

    /**
     * 默认分页查询
     *
     * @param videosDTO
     * @return
     */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody VideosDTO videosDTO) {
        return ReturnMsg.ok(baseService.basePage(videosDTO));
    }

    /**
     * 根据XML中的sql语句进行查询
     *
     * @param videosDTO
     * @return
     */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody VideosDTO videosDTO) {
        return ReturnMsg.ok(baseService.findList(videosDTO));
    }

    @Override
    protected void beforeSave(VideosDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    @GetMapping("/click")
    public ReturnMsg getClickVideo() {
        LambdaQueryWrapper<VideosDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(VideosDTO::getSeeNum, VideosDTO::getCreateTime).last("limit 4");
        return ReturnMsg.ok(this.baseService.list(queryWrapper));
    }

    @GetMapping("/addClick/{id}")
    public ReturnMsg addClick(@PathVariable Long id) {
        VideosDTO videosDTO = this.baseService.getById(id);
        Integer seeNum = videosDTO.getSeeNum();
        if (seeNum == null) seeNum = 0;
        seeNum++;
        videosDTO.setSeeNum(seeNum);
        this.baseService.updateById(videosDTO);
        return ReturnMsg.ok();
    }


    @GetMapping("/all/types")
    public ReturnMsg allTypes() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<TypesDTO> dtos = typesService.list();
        for (TypesDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getTypeName());
            list.add(baseSelectEntity);
        }
        return ReturnMsg.ok(list);
    }

}

