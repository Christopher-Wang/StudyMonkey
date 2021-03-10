package com.studymonkey.surveychimp.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.studymonkey.surveychimp.entity.User;
import com.studymonkey.surveychimp.service.UserService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "/allUsers")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    /*
    Example:
    http://localhost:8080/users/addUser
    {
        "userName": "chris1",
        "userEmail": "chris1@gmail.com"
    }
     */
    @PostMapping(value = "/addUser")
    public List<Map<String, Object>> addUser(@RequestBody User user) {
       return userService.insertUser(user);
    }

    /*
    Example:
    http://localhost:8080/users/updateUser
    {
        "id": 1,
        "userName": "newchris1",
        "userEmail": "chris1@gmail.com"
    }
     */
    @PutMapping(value = "/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    /*
    Example:
    http://localhost:8080/users/deleteUser/2
     */
    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
