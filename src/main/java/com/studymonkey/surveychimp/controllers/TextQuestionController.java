package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("textQuestion")
public class TextQuestionController {

    @Resource
    QuestionService questionService;

    @GetMapping("findQuestion")
    public Question findQuestion(@RequestParam(name = "surveryId") int surveryId, @RequestParam(name = "questionOrder") int questionOrder) {
        return questionService.findQuestion(surveryId, questionOrder);
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

    @PutMapping("updateQuestion")
    public void updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
    }

    @DeleteMapping("deleteQuestion")
    public void deleteQuestion(@RequestParam(name = "surveyId") int surveyId, @RequestParam(name = "questionOrder") int questionOrder) {
        questionService.deleteQuestion(surveyId, questionOrder);
    }
}
