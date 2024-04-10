package com.jsss.qianfan.service;

import com.jsss.qianfan.dao.ChatMapper;
import com.jsss.qianfan.entity.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatMapper chatMapper;

    public List<Conversation> searchByTagId(Integer tagId) {
        return chatMapper.getByTagId(tagId);
    }

    public Conversation getChat(Integer id) {
        return chatMapper.select(id);
    }

    public void addChat(Conversation conversation) {
        chatMapper.insert(conversation);
    }

    public void updateChat(Conversation conversation) {
        chatMapper.update(conversation);
    }


}
