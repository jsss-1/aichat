package com.jsss.qianfan.controller;

import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.common.ResponseModel;
import com.jsss.entity.User;
import com.jsss.qianfan.entity.Tag;
import com.jsss.qianfan.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
@CrossOrigin(origins = "${jsss.web.path}", allowedHeaders = "*", allowCredentials = "true")
public class TagController implements ErrorCode {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TagService tagService;


    @GetMapping("/tagList")
    public ResponseModel getTags(String token) {

        User user = null;
        if (StringUtils.isNotEmpty(token)) {
            user = (User) redisTemplate.opsForValue().get(token);
        }

        if (user == null) {
            throw new BusinessException(USER_NOT_LOGIN, "用户未登录");
        }

        List<Tag> tags = tagService.searchByUserId(user.getUserId());

        return new ResponseModel(tags);


    }


    @PostMapping("/addTag")
    public ResponseModel addTag(String token) {
        User user = null;
        if (StringUtils.isNotEmpty(token)) {
            user = (User) redisTemplate.opsForValue().get(token);
        }

        if (user == null) {
            throw new BusinessException(USER_NOT_LOGIN, "用户未登录");
        }

        String tagName = "新对话";

        Tag tag = new Tag(null, user.getUserId(), tagName, 0);
        tagService.addTag(tag);

        return new ResponseModel("添加成功");
    }

    @PostMapping("/modify")
    public ResponseModel modifyTag(Integer tagId, String tagName) {
        if (StringUtils.isEmpty(tagName)){
            throw new BusinessException(PARAMETER_ERROR, "缺失新的tag名");
        }
        tagService.updateTagName(tagId, tagName);
        return new ResponseModel("修改成功");
    }

    @PostMapping("/top")
    public ResponseModel topTag(Integer tagId, Integer top) {
        tagService.updateTagTop(tagId, top);
        String res = top == 1 ? "置顶成功" : "取消置顶成功";
        return new ResponseModel(res);
    }

    @GetMapping("/delete")
    public ResponseModel deleteTag(Integer tagId) {
        tagService.deleteTag(tagId);
        return new ResponseModel("删除成功");
    }

    @GetMapping("/search")
    public ResponseModel searchTag(String token, String data) {


        User user = null;
        if (StringUtils.isNotEmpty(token)) {
            user = (User) redisTemplate.opsForValue().get(token);
        }

        if (user == null) {
            throw new BusinessException(USER_NOT_LOGIN, "用户未登录");
        }


        List<Tag> tags = tagService.searchTag(user.getUserId(),data);

        return new ResponseModel(tags);

    }


}
