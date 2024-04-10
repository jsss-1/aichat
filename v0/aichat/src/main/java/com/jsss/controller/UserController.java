package com.jsss.controller;

import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.common.ResponseModel;
import com.jsss.entity.User;
import com.jsss.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "${jsss.web.path}", allowedHeaders = "*", allowCredentials = "true")
public class UserController implements ErrorCode {

    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel login(String data,String password){

        if (data == null || "".equals(data) || password == null || "".equals(password)) {
            throw new BusinessException(PARAMETER_ERROR, "账号或密码不能为空");
        }

        User user = userService.login(data, password);

        String token = UUID.randomUUID().toString().replace("-", "");

        redisTemplate.opsForValue().set(token, user, 1, TimeUnit.DAYS);

        return new ResponseModel(token);
    }





    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel register(User user){
        userService.register(user);
        return new ResponseModel();

    }


    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel logout(String token) {
        if (StringUtils.isNotEmpty(token)) {
            redisTemplate.delete(token);
        }
        return new ResponseModel();
    }

    @RequestMapping(path = "/status", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getUser(String token) {

        User user = null;
        if (StringUtils.isNotEmpty(token)) {
            user = (User) redisTemplate.opsForValue().get(token);
        }
        return new ResponseModel(user);
    }


}
