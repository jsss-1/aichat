package com.jsss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsss.common.BusinessException;
import com.jsss.common.ErrorCode;
import com.jsss.common.Toolbox;
import com.jsss.dao.UserMapper;
import com.jsss.entity.User;
import com.jsss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService, ErrorCode {

    @Autowired
    UserMapper userMapper;

    public User login(String data, String password) {
        if (data == null || "".equals(data) || password == null || "".equals(password)) {
            throw new BusinessException(PARAMETER_ERROR, "账号或密码不能为空");
        }

        String username = "";
        String email = "";
        String phone = "";

        if (data.contains("@")) {
            // 输入的登录信息包含@，认为是邮箱登录
            email = data;
        } else if (data.length() == 11 && data.matches("\\d+")) {
            // 输入的登录信息是11位纯数字，认为是手机号登录
            phone = data;
        } else {
            // 其他情况认为是用户名登录
            username = data;
        }


        User user = null;
        Map<String, String> map = new HashMap<>();
        if (!"".equals(username)) {
            user = selectUserByUsername(username);
        } else if (!"".equals(email)) {
            user = selectUserByEmail(email);
        } else if (!"".equals(phone)) {
            user = selectUserByPhone(phone);
        } else {
            throw new BusinessException(PARAMETER_ERROR, "账号或密码不能为空");
        }

        //判断是否有这个用户
        if (user == null ) {
            throw new BusinessException(USER_LOGIN_FAILURE, "用户不存在");
        }

        //密码加密
        if (!user.getPassword().equals(Toolbox.md5(password))){
            throw new BusinessException(USER_LOGIN_FAILURE, "密码错误");
        }


        user.setLoginTime(new Timestamp(new Date().getTime()));

        updateLoginTime(user);

        return user;
    }

    @Override
    public void register(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (username.contains("@")) {
            throw new BusinessException(PARAMETER_ERROR,"用户名不能包含@");
        }

        if ( username.matches("\\d+")) {
            throw new BusinessException(PARAMETER_ERROR,"用户名不能是纯数字");
        }

        User userByUsername = selectUserByUsername(username);
        if (userByUsername!=null){
            throw new BusinessException(USER_LOGIN_FAILURE,"用户名已存在");
        }

        //密码加密
        user.setPassword(Toolbox.md5(password));

        //默认昵称和用户名一样
        user.setNickname(user.getUsername());
        user.setUserGroup("普通用户");
        user.setCreateTime(new Timestamp(new Date().getTime()));
        userMapper.insert(user);
    }

    @Override
    public User selectUserById(Integer userId) {
        User user=userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(USER_UPDATE_FAIL, "账号不存在");
        }
        if (user.getState() != 1) {
            throw new BusinessException(USER_UPDATE_FAIL, "账号不可用");
        }
        return user;
    }



    private int updateUser(User user) {
        return userMapper.updateById(user);
    }


    @Override
    public int updateLoginTime(User user) {
        return updateUser(user);
    }

    private User getUser(Integer id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(USER_UPDATE_FAIL, "账号不存在");
        }
        if (user.getState() != 1) {
            throw new BusinessException(USER_UPDATE_FAIL, "账号不可用");
        }
        return user;
    }


    @Override
    public int updatePhone(Integer id,String phone) {
        User userByPhone = selectUserByPhone(phone);
        if (userByPhone!=null){
            throw new BusinessException(USER_UPDATE_FAIL, "该手机已经绑定账号");
        }

        User user = getUser(id);
        if(user.getPhoneState()!=0){
            throw new BusinessException(USER_UPDATE_FAIL,"账号已经被绑定手机");
        }
        user.setPhone(phone);
        user.setPhoneState(1);
        return updateUser(user);
    }



    @Override
    public int updateEmail(Integer id,String email) {

        User userByEmail = selectUserByEmail(email);
        if (userByEmail!=null){
            throw new BusinessException(USER_UPDATE_FAIL, "该邮箱已经绑定账号");
        }

        User user = getUser(id);
        if(user.getEmailState()!=0){
            throw new BusinessException(USER_UPDATE_FAIL,"账号已经被绑定邮箱");
        }
        user.setEmail(email);
        user.setEmailState(1);
        return updateUser(user);
    }

    @Override
    public int updateInfo(Integer id,String username,String nickname,String oldPassword,String password) {

        if (username.contains("@")) {
            throw new BusinessException(PARAMETER_ERROR,"用户名不能包含@");
        }

        if ( username.matches("\\d+")) {
            throw new BusinessException(PARAMETER_ERROR,"用户名不能是纯数字");
        }

        User user = getUser(id);
        user.setUsername(username);
        user.setNickname(nickname);

        //密码加密
        if (!user.getPassword().equals(Toolbox.md5(oldPassword))){
            throw new BusinessException(USER_UPDATE_FAIL,"旧密码不对");
        }
        //密码加密
        user.setPassword(Toolbox.md5(password));
        return updateUser(user);
    }

    @Override
    public int updateUserGroup(Integer id,String userGroup) {
        User user = getUser(id);
        if (!"普通用户".equals(user.getUserGroup())){
            throw new BusinessException(HAVE_BIND,"用户已经绑定其他角色");
        }
        user.setUserGroup(userGroup);
        return updateUser(user);
    }







    public List<User> list(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.selectList(wrapper);
    }

    @Override
    public User selectUserByPhone(String phone) {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(params);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User selectUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(params);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User selectUserByUsername(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(params);
        return userMapper.selectOne(wrapper);
    }


}
