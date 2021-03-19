package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.service.SurveyMcQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("mcQuestion")
public class McQuestionController {

    @Resource
    SurveyMcQuestionService surveyMcQuestionService;

    /*
    Example:
    http://localhost:8080/mcQuestion/findQuestion

    {
        "surveyId": 2,
        "questionOrder": 16
    }
     */
    @GetMapping("findQuestion")
    public Question findQuestion(@RequestBody Question question) {
        return surveyMcQuestionService.findMcQuestion(question.getSurveyId(), question.getQuestionOrder());
    }

    /*
    Example:
    http://localhost:8080/mcQuestion/addQuestion
    {
        "surveyId": 2,
        "question": "New MC question",
        "questionType": "MULTIPLE_CHOICE",
        "mcOption": ["first option", "second option"]
    }
    */
    @PostMapping("addQuestion")
    public List<Map<String, Object>> addQuestion(@RequestBody McQuestion question) {
        return surveyMcQuestionService.insertMcQuestion(question);
    }

    /*
    Example:
    http://localhost:8080/mcQuestion/updateQuestion
    {
        "surveyId": 5,
        "question": "Hee hee question 2 UPDATED",
        "questionType": "MULTIPLE_CHOICE",
        "mcOption": ["UPDATED option text"],
        "questionOrder": 15,
        "optionId": 26
    }
    */
    @PutMapping("updateQuestion")
    public ResponseEntity<?> updateQuestion(@RequestBody McQuestion question) {
        if(question.getMcOption().size() > 1) {
            return ResponseEntity.badRequest().build();
        }
        surveyMcQuestionService.updateMcQuestion(question);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    /*
    Example:
    http://localhost:8080/mcQuestion/deleteQuestion
    {
        "surveyId": 5,
        "questionOrder": 26
    }
     */
    @DeleteMapping("deleteQuestion")
    public void deleteQuestion(@RequestBody Question question) {
        surveyMcQuestionService.deleteMcQuestion(question.getSurveyId(), question.getQuestionOrder());
    }

}
