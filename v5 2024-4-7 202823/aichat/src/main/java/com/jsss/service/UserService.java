package com.jsss.service;


import com.jsss.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User login(String data, String password);

    void register(User user);


    public List<User> list();

    User selectUserByPhone(String phone);


    User selectUserByEmail(String email);

    User selectUserByUsername(String username);


    User selectUserById(Integer userId);


    public int updateLoginTime(User user);

    int updatePhone(Integer id,String phone);

    int updateEmail(Integer id,String email);

    int updateInfo(Integer id,String username,String nickname,String oldPassword,String password);

    int updateUserGroup(Integer id, String userGroup);




}
