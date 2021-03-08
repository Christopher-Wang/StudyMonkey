package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.user.UserDao;
import com.studymonkey.surveychimp.dao.user.UserDaoImpl;
import com.studymonkey.surveychimp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserService {

    UserDao userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User getUser(int userId) {
        return userDao.findUser(userId);
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void executeUpdateUser(User user) {
        userDao.executeUpdateUser(user);
    }

    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }
}
