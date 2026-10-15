package com.statute.server.controller;

import com.statute.server.common.BaseController;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.AdminDTO;
import com.statute.server.service.AdminService;
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
/**
* @description: 管理员对外接口层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController<AdminService, AdminDTO> {


    @Override
    @Resource(name = "adminService")
    protected void setBaseService(AdminService adminService) {
        this.baseService = adminService;
    }

    /**
    * 默认分页查询
    * @param adminDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody AdminDTO adminDTO) {
        return ReturnMsg.ok(baseService.basePage(adminDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param adminDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody AdminDTO adminDTO) {
        return ReturnMsg.ok(baseService.findList(adminDTO));
    }

    @Override
    protected void beforeSave(AdminDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

}

