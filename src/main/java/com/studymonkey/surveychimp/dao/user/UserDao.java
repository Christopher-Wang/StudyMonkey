package com.studymonkey.surveychimp.dao.user;

import com.studymonkey.surveychimp.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public User findUser(int userId);
    public void insertUser(User user);
    public void updateUser(User user);
    public void executeUpdateUser(User user);
    public void deleteUser(int userId);
}
