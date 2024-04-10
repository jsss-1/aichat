package com.jsss.qianfan.controller;

import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.common.ResponseModel;
import com.jsss.qianfan.entity.Conversation;
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
import java.util.concurrent.CompletableFuture;


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
    public ResponseModel getChatList(Integer tagId){
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


        Conversation conversation = new Conversation(null, tagId,username, content, "回复中...", format(new Date()));
        chatService.addChat(conversation);


        // 异步处理AI回复
        CompletableFuture.runAsync(() -> {
            Integer id=conversation.getId();

            String res = null;
            try {
                res = qianfanUtil.addMessage(content);
            } catch (Exception e) {
                res = "回复失败";
            }
            Conversation aiConversation = new Conversation();
            aiConversation.setId(id);
            aiConversation.setBotMessage(res);
            chatService.updateChat(aiConversation);
        });

        return new ResponseModel(conversation);

    }


    @GetMapping("/getChat")
    public ResponseModel getChat(Integer id){

        Conversation chat = chatService.getChat(id);
        return new ResponseModel(chat);

    }

    @PostMapping("/updateStop")
    public ResponseModel updateStop(Integer id,String botMessage){
        Conversation conversation=new Conversation();
        conversation.setId(id);
        conversation.setBotMessage(botMessage);
        chatService.updateChat(conversation);
        return new ResponseModel();
    }

    @GetMapping("/info")
    public ResponseModel info(Integer tagId){
        Tag tag = tagService.searchById(tagId);
        return new ResponseModel(tag);
    }

}
