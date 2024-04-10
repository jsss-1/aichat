package com.jsss.qianfan.dao;

import com.jsss.qianfan.entity.Conversation;
import com.jsss.qianfan.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {



    @Insert("INSERT INTO tag (user_id, tag_name,top) VALUES (#{userId},#{tagName},#{top})")
    void insert(Tag tag);

    @Select("SELECT * FROM tag WHERE id = #{id}")
    Tag getById(Integer id);

    @Select("SELECT * FROM tag WHERE user_id = #{userId} ORDER BY top DESC,id DESC")
    List<Tag> getByUserId(Integer userId);

    @Update("UPDATE tag SET tag_name = #{tagName} WHERE id = #{tagId}")
    int updateName(Integer tagId, String tagName);

    @Update("UPDATE tag SET top = #{top} WHERE id = #{tagId}")
    int updateTop(Integer tagId, Integer top);

    @Delete("DELETE FROM tag WHERE id = #{tagId}")
    int delete(Integer tagId);

    @Select("SELECT * FROM tag WHERE user_id = #{userId} AND tag_name LIKE CONCAT('%',#{data},'%')")
    List<Tag> searchTag(Integer userId, String data);

}
