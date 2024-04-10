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

    public void addChat(Conversation conversation) {
        chatMapper.insert(conversation);
    }

    public List<Conversation> searchByTagId(Integer tagId) {
        return chatMapper.getByTagId(tagId);
    }
}
