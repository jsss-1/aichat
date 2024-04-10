package com.jsss.controller.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jsss.common.ErrorCode;
import com.jsss.common.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor, ErrorCode {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String token = request.getParameter("token");

        if (token == null || StringUtils.isBlank(token) || !redisTemplate.hasKey(token)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            Map<Object, Object> data = new HashMap<>();
            data.put("code", USER_NOT_LOGIN);
            data.put("message", "请先登录！");
            ResponseModel model = new ResponseModel(ResponseModel.STATUS_FAILURE, data);
            writer.write(JSONObject.toJSONString(model));

            return false;
        }
        return true;
    }
}
