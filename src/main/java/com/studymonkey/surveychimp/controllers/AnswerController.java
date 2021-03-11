package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.service.SurveyAnswerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Resource
    SurveyAnswerService surveyAnswerService;

    /*
    Example:
    http://localhost:8080/answers/allAnswers
     */
    @GetMapping(value = "/allAnswers")
    public List<Answer> getAllAnswers() {
        return surveyAnswerService.findAll();
    }

    /*
    Example:
    http://localhost:8080/answers/surveyAnswers
    {
        "id": 5
    }
     */
    @GetMapping(value = "/surveyAnswers")
    public List<Answer> getSurveyAnswers(@RequestBody Survey survey) {
        return surveyAnswerService.findAllAnswersForSurvey(survey.getId());
    }

    /*
    Example:
    http://localhost:8080/answers/addMCAnswer

    {
        "surveyId": 2,
        "questionOrder": 1,
        "userId": 1,
        "mcOptionIdAnswer": 1
    }
     */
    @PostMapping(value = "/addMCAnswer")
    public List<Map<String, Object>> addMCAnswer(@RequestBody McAnswer answer) {
        return surveyAnswerService.insertMcAnswer(answer);
    }


    /*
    Example:
    http://localhost:8080/answers/addTextAnswer
    {
        "surveyId": 2,
        "questionOrder": 2,
        "userId": 1,
        "questionAnswer": "This is my answer!!!1"
    }
     */
    @PostMapping(value = "/addTextAnswer")
    public void addTextAnswer(@RequestBody TextAnswer answer) {
        surveyAnswerService.insertTextAnswer(answer);
    }
}
