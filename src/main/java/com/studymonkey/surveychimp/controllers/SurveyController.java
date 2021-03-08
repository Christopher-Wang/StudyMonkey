package com.studymonkey.surveychimp.controllers;

import java.util.List;

import javax.annotation.Resource;

import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.service.SurveyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Resource
    SurveyService surveyService;

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
    public void createSurvey(@RequestBody Survey survey) {
        surveyService.insertSurvey(survey);
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

    // This was not tested yet. Not needed for now
    @PutMapping(value = "/executeUpdateSurvey")
    public void executeUpdateSurvey(@RequestBody Survey survey) {
        surveyService.executeUpdateSurvey(survey);
    }

    /*
    Example:
    http://localhost:8080/survey/deleteSurvey/4
     */
    @DeleteMapping(value = "/deleteSurvey/{id}")
    public void deleteSurvey(@PathVariable int id) {
        surveyService.deleteSurvey(id);
    }
}
