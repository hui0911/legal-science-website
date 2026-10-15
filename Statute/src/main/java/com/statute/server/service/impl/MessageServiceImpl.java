package com.statute.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.statute.server.dao.MessageDao;
import com.statute.server.dto.MessageDTO;
import com.statute.server.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 留言信息业务层实现类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageDTO> implements MessageService {

    @Override
    public Page<MessageDTO> basePage(MessageDTO messageDTO) {
        if (null != messageDTO.getCurrent() && null != messageDTO.getPageSize())
            messageDTO.setCurrent((messageDTO.getCurrent() - 1) * messageDTO.getPageSize());
        List<MessageDTO> list = this.baseMapper.findList(messageDTO);
        int count = this.baseMapper.findPageCount(messageDTO);
        Page<MessageDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<MessageDTO> findList(MessageDTO messageDTO) {
        return this.baseMapper.findList(messageDTO);
    }

}
