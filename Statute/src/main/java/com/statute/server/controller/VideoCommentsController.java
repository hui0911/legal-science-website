package com.statute.server.controller;

import com.statute.server.common.BaseController;
import com.statute.server.common.ReturnMsg;
import com.statute.server.dto.VideoCommentsDTO;
import com.statute.server.service.VideoCommentsService;
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
* @description: 视频评论表对外接口层
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@RestController
@RequestMapping("/videoComments")
public class VideoCommentsController extends BaseController<VideoCommentsService, VideoCommentsDTO> {


    @Override
    @Resource(name = "videoCommentsService")
    protected void setBaseService(VideoCommentsService videoCommentsService) {
        this.baseService = videoCommentsService;
    }

    /**
    * 默认分页查询
    * @param videoCommentsDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody VideoCommentsDTO videoCommentsDTO) {
        return ReturnMsg.ok(baseService.basePage(videoCommentsDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param videoCommentsDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody VideoCommentsDTO videoCommentsDTO) {
        return ReturnMsg.ok(baseService.findList(videoCommentsDTO));
    }

    @Override
    protected void beforeSave(VideoCommentsDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    @PostMapping(value = "/allComments")
    public ReturnMsg allComments(@RequestBody VideoCommentsDTO commentsDTO) {
        List<VideoCommentsDTO> baseServiceList = baseService.findList(commentsDTO);
        if (CollectionUtil.isNotEmpty(baseServiceList)) {
            for (VideoCommentsDTO dto : baseServiceList) {
                VideoCommentsDTO query = new VideoCommentsDTO();
                query.setParentId(dto.getId());
                dto.setVideoCommentsDTOS(this.baseService.findList(query));
            }
        }
        return ReturnMsg.ok(baseServiceList);
    }

}

