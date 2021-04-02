package com.studymonkey.surveychimp.controllers;

import com.studymonkey.surveychimp.entity.questions.*;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.wrapper.McQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.QuestionWrapper;
import com.studymonkey.surveychimp.repositories.McOptionRepository;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/questionJson")
public class QuestionController {

    public final McOptionRepository mcOptionRepository;
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    QuestionController(McOptionRepository mcOptionRepository, QuestionRepository questionRepository, SurveyRepository surveyRepository) {
        this.mcOptionRepository = mcOptionRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @PostMapping("text")
    @ResponseBody
    public ResponseEntity createTextQuestion(@RequestBody QuestionWrapper questionWrapper) throws NoSuchAlgorithmException {
        Survey s = this.surveyRepository.findById(questionWrapper.getSurveyId());
        if (s == null) return ResponseEntity.badRequest().build();

        Question q = questionWrapper.getQuestion();
        QuestionType type = q.getQuestionType();

        if(type != QuestionType.TEXT) {
            return ResponseEntity.badRequest().build();
        }

        q.setSurvey(s);
        s.addQuestion(q);

        this.questionRepository.save(q);
        this.surveyRepository.save(s);

        return new ResponseEntity<>("New text question created",HttpStatus.CREATED);
    }

    @PostMapping("mc")
    @ResponseBody
    public ResponseEntity<String> createMcQuestion(@RequestBody McQuestionWrapper questionWrapper) throws NoSuchAlgorithmException {
        Survey s = this.surveyRepository.findById(questionWrapper.getSurveyId());
        if (s == null) return ResponseEntity.badRequest().build();

        Question q = questionWrapper.getQuestion();
        QuestionType type = q.getQuestionType();

        if(type != QuestionType.MULTIPLE_CHOICE) {
            return ResponseEntity.badRequest().build();
        }

        q.setSurvey(s);
        s.addQuestion(q);

        this.questionRepository.save(q);
        this.surveyRepository.save(s);
        for(McOption option : questionWrapper.getQuestion().getMcOption()) {
            this.mcOptionRepository.save(option);
        }

        return new ResponseEntity<>("New multiple choice question created",HttpStatus.CREATED);
    }

}
