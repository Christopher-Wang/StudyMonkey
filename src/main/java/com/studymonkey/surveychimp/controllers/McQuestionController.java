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
@RequestMapping("mcQuestion")
public class McQuestionController {

    @Resource
    SurveyMcQuestionService surveyMcQuestionService;

    @GetMapping("findQuestion")
    public Question findQuestion(@RequestParam(name = "surveryId") int surveryId, @RequestParam(name = "questionOrder") int questionOrder) {
        return surveyMcQuestionService.findMcQuestion(surveryId, questionOrder);
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
        "surveyId": 2,
        "question": "LSDSDS",
        "questionType": "MULTIPLE_CHOICE",
        "mcOption": ["Moe"],
        "questionOrder": 15,
        "optionId": 5
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

    @DeleteMapping("deleteAllQuestion")
    public void deleteQuestion(@RequestParam(name = "surveyId") int surveyId, @RequestParam(name = "questionOrder") int questionOrder) {
        surveyMcQuestionService.deleteMcQuestion(surveyId, questionOrder);
    }

}
