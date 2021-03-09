package com.studymonkey.surveychimp.dao.user;

import com.studymonkey.surveychimp.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();
    public User findUser(int userId);
    public List<Map<String, Object>> insertUser(User user);
    public void updateUser(User user);
    public void executeUpdateUser(User user);
    public void deleteUser(int userId);
}
