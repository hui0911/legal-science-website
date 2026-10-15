package com.statute.server.controller;

import com.statute.server.common.BaseController;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.NoticeCommentsDTO;
import com.statute.server.dto.VideoCommentsDTO;
import com.statute.server.service.NoticeCommentsService;
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
* @description: 资讯评论表对外接口层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@RestController
@RequestMapping("/noticeComments")
public class NoticeCommentsController extends BaseController<NoticeCommentsService, NoticeCommentsDTO> {


    @Override
    @Resource(name = "noticeCommentsService")
    protected void setBaseService(NoticeCommentsService noticeCommentsService) {
        this.baseService = noticeCommentsService;
    }

    /**
    * 默认分页查询
    * @param noticeCommentsDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody NoticeCommentsDTO noticeCommentsDTO) {
        return ReturnMsg.ok(baseService.basePage(noticeCommentsDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param noticeCommentsDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody NoticeCommentsDTO noticeCommentsDTO) {
        return ReturnMsg.ok(baseService.findList(noticeCommentsDTO));
    }

    @Override
    protected void beforeSave(NoticeCommentsDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    @PostMapping(value = "/allComments")
    public ReturnMsg allComments(@RequestBody NoticeCommentsDTO commentsDTO) {
        List<NoticeCommentsDTO> baseServiceList = baseService.findList(commentsDTO);
        if (CollectionUtil.isNotEmpty(baseServiceList)) {
            for (NoticeCommentsDTO dto : baseServiceList) {
                NoticeCommentsDTO query = new NoticeCommentsDTO();
                query.setParentId(dto.getId());
                dto.setNoticeCommentsDTOS(this.baseService.findList(query));
            }
        }
        return ReturnMsg.ok(baseServiceList);
    }

}

