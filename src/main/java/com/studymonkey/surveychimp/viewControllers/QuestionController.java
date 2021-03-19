package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.questions.TextQuestion;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "question")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    public QuestionController(QuestionRepository questionRepository,
                              SurveyRepository surveyRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/question/{id}")
    @ResponseBody
    public Survey getQuestion(@PathVariable long id) {
        Question q = this.questionRepository.findById(id);
        return q.getSurvey();
    }

    @PostMapping("/question/{id}")
    @ResponseBody
    public Survey postQuestion(@PathVariable long id, @RequestBody Object body) {
        Survey s = this.surveyRepository.findById(id);

        Question q = new TextQuestion("I am Question", QuestionType.TEXT);
        if (s == null){
            return null;
        }
        q.setSurvey(s);
        s.addQuestion(q);
        this.questionRepository.save(q);
        this.surveyRepository.save(s);
        return s;
    }
}
