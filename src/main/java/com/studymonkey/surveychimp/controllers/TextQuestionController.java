package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("textQuestion")
public class TextQuestionController {

    @Resource
    QuestionService questionService;

    @GetMapping("findQuestion")
    public Question findQuestion(@RequestParam(name = "surveryId") int surveryId, @RequestParam(name = "questionOrder") int questionOrder) {
        return questionService.findQuestion(surveryId, questionOrder);
    }

    @PostMapping("addQuestion")
    public void addQuestion(@RequestBody Question question) {
        questionService.insertQuestion(question);
    }

    @PutMapping("updateQuestion")
    public void updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
    }

    @DeleteMapping("deleteQuestion")
    public void deleteQuestion(@RequestParam(name = "surveyId") int surveyId, @RequestParam(name = "questionOrder") int questionOrder) {
        questionService.deleteQuestion(surveyId, questionOrder);
    }
}
