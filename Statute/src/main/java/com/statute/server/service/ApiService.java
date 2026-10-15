package com.statute.server.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.statute.server.common.util.AiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author azhou
 * @apiNote
 */
@Component
public class ApiService {

    @Autowired
    private AiChatClient api;

    public String query(String content) {
        return api.chat(content);
    }


}
