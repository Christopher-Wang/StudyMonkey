package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("textQuestion")
public class TextQuestionController {

    @Resource
    QuestionService questionService;

    /*
    Example:
    http://localhost:8080/textQuestion/findQuestion
    {
        "surveyId": 2,
        "questionOrder": 3
    }
     */
    @GetMapping("findQuestion")
    public Question findQuestion(@RequestBody Question question) {
        return questionService.findQuestion(question.getSurveyId(), question.getQuestionOrder());
    }

    /*
    EXAMPLE
    http://localhost:8080/textQuestion/addQuestion
    {
        "surveyId": 5,
        "question": "making a question?",
        "questionType": "TEXT"
    }
    */
    @PostMapping("addQuestion")
    public List<Map<String, Object>> addQuestion(@RequestBody Question question) {
        return questionService.insertQuestion(question);
    }

    /*
    Example:
    http://localhost:8080/textQuestion/updateQuestion
    {
        "questionOrder": 28,
        "surveyId": 5,
        "question": "What is your name?",
        "questionType": "TEXT"
    }
     */
    @PutMapping("updateQuestion")
    public void updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
    }

    /*
    Example:
    http://localhost:8080/textQuestion/deleteQuestion
    {
        "surveyId": 5,
        "questionOrder": 28
    }
     */
    @DeleteMapping("deleteQuestion")
    public void deleteQuestion(@RequestBody Question question) {
        questionService.deleteQuestion(question.getSurveyId(), question.getQuestionOrder());
    }
}
