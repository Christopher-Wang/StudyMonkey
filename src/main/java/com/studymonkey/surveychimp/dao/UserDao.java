package com.studymonkey.surveychimp.dao;

import com.studymonkey.surveychimp.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public void insertUser(User user);
    public void updateUser(User user);
    public void executeUpdateEmployee(User user);
    public void deleteUser(User user);
}
