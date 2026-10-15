package com.statute.server.controller;

import com.statute.server.common.BaseController;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.SubscribesDTO;
import com.statute.server.service.SubscribesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.statute.server.common.BaseSelectEntity;
import java.util.ArrayList;
import com.statute.server.service.UsersService;
import com.statute.server.dto.UsersDTO;
import com.statute.server.service.LawyersService;
import com.statute.server.dto.LawyersDTO;
import javax.annotation.Resource;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import com.statute.server.common.BaseSelectEntity;
import cn.hutool.core.collection.CollectionUtil;
import java.util.Date;
/**
* @description: 预约信息对外接口层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@RestController
@RequestMapping("/subscribes")
public class SubscribesController extends BaseController<SubscribesService, SubscribesDTO> {

    @Autowired
    private UsersService usersService;
    @Autowired
    private LawyersService lawyersService;

    @Override
    @Resource(name = "subscribesService")
    protected void setBaseService(SubscribesService subscribesService) {
        this.baseService = subscribesService;
    }

    /**
    * 默认分页查询
    * @param subscribesDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody SubscribesDTO subscribesDTO) {
        return ReturnMsg.ok(baseService.basePage(subscribesDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param subscribesDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody SubscribesDTO subscribesDTO) {
        return ReturnMsg.ok(baseService.findList(subscribesDTO));
    }

    @Override
    protected void beforeSave(SubscribesDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    @GetMapping("/all/users")
    public ReturnMsg allUsers() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<UsersDTO> dtos = usersService.list();
            for (UsersDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getNickName());
            list.add(baseSelectEntity);
        }
        return ReturnMsg.ok(list);
    }

    @GetMapping("/all/lawyers")
    public ReturnMsg allLawyers() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<LawyersDTO> dtos = lawyersService.list();
            for (LawyersDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getName());
            list.add(baseSelectEntity);
        }
        return ReturnMsg.ok(list);
    }

}

