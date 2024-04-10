package com.jsss.aichat;

import com.jsss.qianfan.entity.Conversation;
import com.jsss.qianfan.service.ChatService;
import com.jsss.qianfan.util.QianfanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
class ChatServiceTests {


    @Autowired
    ChatService chatService;

    private String format(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }



    @Test
    void testInsert() {
        Integer tagId=1;
        String username="user";
        String content="你好";

        Conversation conversation = new Conversation(null, tagId,username, content, null, format(new Date()));
        chatService.addChat(conversation);
        System.out.println(conversation.getId());

    }

    @Test
    void testUpdate() {
        Integer id=14;

        String botMessage="你好";

        Conversation conversation = new Conversation();
        conversation.setId(id);
        conversation.setBotMessage(botMessage);

        chatService.updateChat(conversation);


    }

    @Test
    void testGet() {
        Integer id=14;
        Conversation chat = chatService.getChat(id);
        System.out.println(chat);
    }

}
