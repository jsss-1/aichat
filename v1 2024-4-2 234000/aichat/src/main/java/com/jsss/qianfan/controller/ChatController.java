package com.jsss.qianfan.controller;

import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.common.ResponseModel;
import com.jsss.qianfan.entity.Conversation;
import com.jsss.entity.User;
import com.jsss.qianfan.entity.Tag;
import com.jsss.qianfan.service.ChatService;
import com.jsss.qianfan.service.TagService;
import com.jsss.qianfan.util.QianfanUtil;
import com.jsss.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    TagService tagService;

    @Autowired
    UserService userService;

    private String format(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }



    @GetMapping("/list")
    public ResponseModel getChat(Integer tagId){
        List<Conversation> conversations=chatService.searchByTagId(tagId);
        return new ResponseModel(conversations);
    }



    @PostMapping("/chat")
    public ResponseModel chat(Integer tagId,String content){
        if (tagId==null){
            throw new BusinessException(PARAMETER_ERROR,"没有指定响应的tag");
        }

        if (StringUtils.isEmpty(content)){
            throw new BusinessException(PARAMETER_ERROR,"输入内容不能为空");
        }

        Tag tag = tagService.searchById(tagId);
        if (tag==null){
            throw new BusinessException(NOT_FIND,"没有找到对应的对话");
        }

        String username=userService.selectUserById(tag.getUserId()).getUsername();

        String res = qianfanUtil.addMessage(content);
        Conversation conversation = new Conversation(null, tagId,username, content, res, format(new Date()));
        chatService.addChat(conversation);

        return new ResponseModel();
    }



    @GetMapping("/info")
    public ResponseModel info(Integer tagId){
        Tag tag = tagService.searchById(tagId);
        return new ResponseModel(tag);
    }

}
