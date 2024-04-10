package com.jsss.qianfan.dao;

import com.jsss.qianfan.entity.Conversation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {



    @Insert("INSERT INTO conversation (username, user_message, bot_message, create_time) " +
            "VALUES (#{username}, #{userMessage}, #{botMessage}, #{createTime})")
    void insert(Conversation conversation);


    @Select("SELECT * FROM conversation")
    List<Conversation> getChats();



}
