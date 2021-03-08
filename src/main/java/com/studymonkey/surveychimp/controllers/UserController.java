package com.studymonkey.surveychimp.controllers;

import java.util.List;

import javax.annotation.Resource;

import com.studymonkey.surveychimp.entity.User;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import com.studymonkey.surveychimp.service.SurveyQuestionService;
import com.studymonkey.surveychimp.service.SurveyService;
import com.studymonkey.surveychimp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "/allUsers")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


}
