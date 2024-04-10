package com.jsss.qianfan.controller;

import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.common.ResponseModel;
import com.jsss.qianfan.entity.Conversation;
import com.jsss.entity.User;
import com.jsss.qianfan.service.ChatService;
import com.jsss.qianfan.util.QianfanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("chat")
@CrossOrigin(origins = "${jsss.web.path}", allowedHeaders = "*", allowCredentials = "true")
public class ChatController implements ErrorCode {

    @Autowired
    QianfanUtil qianfanUtil;

    @Autowired
    ChatService chatService;

    @Autowired
    RedisTemplate redisTemplate;

    private String format(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }



    @GetMapping("/list")
    public ResponseModel getChat(){
        List<Conversation> conversations=chatService.search();
        return new ResponseModel(conversations);
    }



    @PostMapping("/chat")
    public ResponseModel chat(String token,String content){
        User user=null;
        if (StringUtils.isEmpty(token)){
            user= (User) redisTemplate.opsForValue().get(token);
        }

        if (user==null){
            throw new BusinessException(PARAMETER_ERROR,"用户没有登录");
        }

        if (StringUtils.isEmpty(content)){
            throw new BusinessException(PARAMETER_ERROR,"输入内容不能为空");
        }


        String username=user.getUsername();

        String res = qianfanUtil.addMessage(content);
        Conversation conversation = new Conversation(null, username, content, res, format(new Date()));
        chatService.addChat(conversation);

        return new ResponseModel();
    }



}
