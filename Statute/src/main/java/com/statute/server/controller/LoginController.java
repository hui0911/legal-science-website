package com.statute.server.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.AdminDTO;
import com.statute.server.dto.LawyersDTO;
import com.statute.server.service.AdminService;
import com.statute.server.service.LawyersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 后台管理登录控制器
 * @author: liwenhui
 * @Create: 2026.04.07 14:09:31
 */
@RestController
@RequestMapping("/manage/login")
public class LoginController {

    static Map<String, String> userInfoMap = new HashMap<>();

    @Autowired
    private AdminService adminService;

    @Autowired
    private LawyersService lawyersService;

    @PostMapping
    public ReturnMsg manageLogin(@RequestBody Map<String, String> param) {
        if (StrUtil.isEmpty(param.get("username")) || StrUtil.isEmpty(param.get("password"))) {
            return ReturnMsg.error("用户名或密码错误!");
        }
        Integer type = Integer.parseInt(param.get("type"));
        Map<String, Object> map = new HashMap<>();
        if (type == 0) {
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setUserName(param.get("username"));
            adminDTO.setPassWord(param.get("password"));
            QueryWrapper<AdminDTO> adminDTOQueryWrapper = new QueryWrapper<>(adminDTO);
            adminDTOQueryWrapper.last("limit 1");
            AdminDTO adminDTOS = adminService.getOne(adminDTOQueryWrapper);
            if (adminDTOS == null) return ReturnMsg.error("用户名或密码错误!");
            map.put("avatar", StrUtil.isNotBlank(adminDTOS.getHeadImg()) ? adminDTOS.getHeadImg() : "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            map.put("introduction", adminDTOS.getNickName() + ",你好!");
            map.put("name", adminDTOS.getNickName());
            map.put("id", adminDTOS.getId());
        } else {
            LambdaQueryWrapper<LawyersDTO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(LawyersDTO::getEmail, param.get("username")).eq(LawyersDTO::getPassword, param.get("password")).last("limit 1");
            LawyersDTO serviceOne = lawyersService.getOne(queryWrapper);
            if (serviceOne == null) return ReturnMsg.error("用户名或密码错误!");
            if (serviceOne.getStatus() != 1) return ReturnMsg.error("账号不正常!");
            map.put("avatar", StrUtil.isNotBlank(serviceOne.getAvatar()) ? serviceOne.getAvatar() : "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            map.put("introduction", serviceOne.getName() + ",你好!");
            map.put("name", serviceOne.getName());
            map.put("id", serviceOne.getId());
        }
        map.put("type", type);
        map.put("roles", Arrays.asList("admin"));
        Map<String, String> returnMap = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        returnMap.put("token", uuid);
        userInfoMap.put(uuid, JSON.toJSONString(map));
        return ReturnMsg.ok(returnMap);
    }

    @GetMapping
    public ReturnMsg getUserInfo(@RequestParam("token") String token) {
        if (!userInfoMap.containsKey(token)) return ReturnMsg.error(401, "尚未登录,请重新登录!");
        return ReturnMsg.ok(JSON.parse(userInfoMap.get(token)));
    }

    @GetMapping("/logout")
    public ReturnMsg logout(@RequestParam("token") String token) {
        userInfoMap.remove(token);
        return ReturnMsg.ok();
    }

}
