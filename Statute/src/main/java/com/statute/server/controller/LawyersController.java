package com.statute.server.controller;

import com.statute.server.common.BaseController;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.LawyersDTO;
import com.statute.server.service.ApiService;
import com.statute.server.service.LawyersService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.statute.server.common.BaseSelectEntity;
import cn.hutool.core.collection.CollectionUtil;

import java.util.Date;
import java.util.Map;

/**
 * @description: 律师信息对外接口层
 * @author: liwenhui
 * @create: 2026.04.07 14:09:31
 */
@RestController
@RequestMapping("/lawyers")
public class LawyersController extends BaseController<LawyersService, LawyersDTO> {

    @Autowired
    private ApiService apiService;


    @Override
    @Resource(name = "lawyersService")

    protected void setBaseService(LawyersService lawyersService) {
        this.baseService = lawyersService;
    }

    /**
     * 默认分页查询
     *
     * @param lawyersDTO
     * @return
     */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody LawyersDTO lawyersDTO) {
        return ReturnMsg.ok(baseService.basePage(lawyersDTO));
    }

    /**
     * 根据XML中的sql语句进行查询
     *
     * @param lawyersDTO
     * @return
     */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody LawyersDTO lawyersDTO) {
        return ReturnMsg.ok(baseService.findList(lawyersDTO));
    }

    @Override
    protected void beforeSave(LawyersDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    @PostMapping(value = "/query")
    public ReturnMsg query(@RequestBody Map<String, String> params) {
        String content = apiService.query(params.get("content"));
        return ReturnMsg.ok(content);
    }

}

