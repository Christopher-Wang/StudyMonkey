package com.studymonkey.surveychimp.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import com.studymonkey.surveychimp.service.SurveyQuestionService;
import com.studymonkey.surveychimp.service.SurveyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Resource
    SurveyService surveyService;

    @Resource
    SurveyQuestionService surveyQuestionService;

    /*
    Example:
    http://localhost:8080/survey/surveyList
     */
    @GetMapping(value = "/surveyList")
    public List<Survey> getSurveys() {
        return surveyService.findAll();
    }

    /*
    Example:
    http://localhost:8080/survey/createSurvey
    {
        "name": "Christophe's Survey 2",
        "description": "Testing 2!",
        "status": "CLOSED"
    }
     */
    @PostMapping(value = "/createSurvey")
    public List<Map<String, Object>> createSurvey(@RequestBody Survey survey) {
        return surveyService.insertSurvey(survey);
    }

    /*
    Example:
    {
        "id": 3,
        "name": "Christophe Updated!!!",
        "description": "CHANGED for the second time",
        "status": "OPEN"
    }
     */
    @PutMapping(value = "/updateSurvey")
    public void updateSurvey(@RequestBody Survey survey) {
        surveyService.updateSurvey(survey);
    }

    /*
    Example:
    http://localhost:8080/survey/deleteSurvey
    {
        "id": 9
    }
     */
    @DeleteMapping(value = "/deleteSurvey")
    public void deleteSurvey(@RequestBody Survey survey) {
        surveyService.deleteSurvey(survey.getId());
    }

    /*
    Example:
    http://localhost:8080/survey/surveyQuestions
    {
        "id": 2
    }
     */
    @GetMapping(value = "/surveyQuestions")
    public SurveyQuestions getSurveyQuestions(@RequestBody Survey survey) {
        return surveyQuestionService.findSurvey(survey.getId());
    }
}
