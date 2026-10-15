package com.statute.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.statute.server.common.BaseController;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.NoticeDTO;
import com.statute.server.dto.VideosDTO;
import com.statute.server.service.NoticeService;
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
 * @description: 资讯信息表对外接口层
 * @author: liwenhui
 * @create: 2026.04.07 14:09:31
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController<NoticeService, NoticeDTO> {

    @Autowired
    private TypesService typesService;

    @Override
    @Resource(name = "noticeService")
    protected void setBaseService(NoticeService noticeService) {
        this.baseService = noticeService;
    }

    /**
     * 默认分页查询
     *
     * @param noticeDTO
     * @return
     */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody NoticeDTO noticeDTO) {
        return ReturnMsg.ok(baseService.basePage(noticeDTO));
    }

    /**
     * 根据XML中的sql语句进行查询
     *
     * @param noticeDTO
     * @return
     */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody NoticeDTO noticeDTO) {
        return ReturnMsg.ok(baseService.findList(noticeDTO));
    }

    @Override
    protected void beforeSave(NoticeDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    /**
     * 最新资讯
     * @return
     */
    @GetMapping("/new")
    public ReturnMsg getNewNotice() {
        LambdaQueryWrapper<NoticeDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(NoticeDTO::getCreateTime).last("limit 8");
        return ReturnMsg.ok(this.baseService.list(queryWrapper));
    }

    @GetMapping("/addClick/{id}")
    public ReturnMsg addClick(@PathVariable Long id) {
        NoticeDTO videosDTO = this.baseService.getById(id);
        Integer seeNum = videosDTO.getSeeNum();
        if (seeNum == null) seeNum = 0;
        seeNum++;
        videosDTO.setSeeNum(seeNum);
        this.baseService.updateById(videosDTO);
        return ReturnMsg.ok();
    }

    /**
     * 最热资讯
     * @return
     */
    @GetMapping("/click")
    public ReturnMsg getClickNotice() {
        LambdaQueryWrapper<NoticeDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(NoticeDTO::getSeeNum, NoticeDTO::getCreateTime).last("limit 8");
        return ReturnMsg.ok(this.baseService.list(queryWrapper));
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

