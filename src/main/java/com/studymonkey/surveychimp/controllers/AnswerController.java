package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;
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

    /*
    Example:
    http://localhost:8080/answers/surveyAnswers/2

     */
    @GetMapping(value = "/surveyAnswers/{id}")
    public List<Answer> getSurveyAnswers(@PathVariable int id) {
//        Question question = new Question();
//        McQuestion mcQuestion = (McQuestion) question;

//        Question question = (Question) new McQuestion();
//        McQuestion mcQuestion = (McQuestion) question;

        return surveyAnswerService.findAllAnswersForSurvey(id);
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
    public void addMCAnswer(@RequestBody McAnswer answer) {
        surveyAnswerService.insertMcAnswer(answer);
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
