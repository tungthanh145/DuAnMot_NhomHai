package com.example.duanmot_nhomhai.dao;

import com.example.duanmot_nhomhai.model.User;

public interface IUser {
    User login(String userName, String passWord);
    // insert
    boolean register(User user);
    boolean update(User user);
    boolean checkUsernameExists(String name);
}
