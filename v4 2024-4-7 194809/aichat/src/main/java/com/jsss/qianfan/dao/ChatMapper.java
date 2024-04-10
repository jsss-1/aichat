package com.jsss.qianfan.dao;

import com.jsss.qianfan.entity.Conversation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChatMapper {


    @Select("SELECT * FROM conversation WHERE tag_id = #{tagId}")
    List<Conversation> getByTagId(Integer tagId);

    @Select("SELECT * FROM conversation WHERE id = #{id} ")
    Conversation select(Integer id);

    @Insert("INSERT INTO conversation (tag_id, username, user_message, bot_message, create_time) " +
            "VALUES (#{tagId},#{username}, #{userMessage}, #{botMessage}, #{createTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Conversation conversation);

    @Update("UPDATE conversation SET bot_message = #{botMessage} where id = #{id}")
    void update(Conversation conversation);


}
