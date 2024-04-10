package com.jsss.qianfan.service;

import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.qianfan.dao.TagMapper;
import com.jsss.qianfan.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements ErrorCode{

    @Autowired
    TagMapper tagMapper;

    public void addTag(Tag tag) {
        tagMapper.insert(tag);
    }

    public Tag searchById(Integer id) {
        return tagMapper.getById(id);
    }

    public List<Tag> searchByUserId(Integer userId) {
        return tagMapper.getByUserId(userId);
    }

    public void updateTagName(Integer tagId, String tagName) {
        int res = tagMapper.updateName(tagId, tagName);
        if (res!=1){
            throw new BusinessException(NOT_FIND,"修改失败");
        }
    }

    public void updateTagTop(Integer tagId, Integer top) {
        int res = tagMapper.updateTop(tagId, top);
        if (res!=1){
            throw new BusinessException(NOT_FIND,"修改失败");
        }
    }

    public void deleteTag(Integer tagId) {
        int res = tagMapper.delete(tagId);
        if (res!=1){
            throw new BusinessException(NOT_FIND,"删除失败");
        }

    }


    public List<Tag> searchTag(Integer userId, String data) {
        return tagMapper.searchTag(userId,data);
    }
}
