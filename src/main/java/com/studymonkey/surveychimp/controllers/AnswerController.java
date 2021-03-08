package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.service.QuestionService;
import com.studymonkey.surveychimp.service.SurveyAnswerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Resource
    SurveyAnswerService surveyAnswerService;

    @GetMapping(value = "/allAnswers")
    public List<Answer> getAllAnswers() {
        return surveyAnswerService.findAll();
    }

    @GetMapping(value = "/surveyAnswers/{id}}")
    public List<Answer> getSurveyAnswers(@PathVariable int id) {
        return surveyAnswerService.findAllAnswersForSurvey(id);
    }

    @PostMapping(value = "/addMCAnswer")
    public void addMCAnswer(McAnswer answer) {
        surveyAnswerService.insertMcAnswer(answer);
    }

    @PostMapping(value = "/addTextAnswer")
    public void addTextAnswer(TextAnswer answer) {
        surveyAnswerService.insertTextAnswer(answer);
    }
}
