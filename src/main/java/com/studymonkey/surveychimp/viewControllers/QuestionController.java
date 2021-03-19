package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
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

    /**
     * Example Request: http://localhost:8080/question
     * @param model the model of the system
     * @return the view for creating a survey
     */
    @GetMapping
    public String surveyForm(@RequestParam(value = "surveyId", required=false) Long surveyId, Model model) {
        if (surveyId == null) surveyId = new Long(0);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("question", new Question());
        return "questioncreate";
    }

    /**
     * Example Request: http://localhost:8080/question
     * @param model the model of the system
     * @return the view for creating a survey
     */
    @PostMapping
    public String test(@ModelAttribute("surveyId") long surveyId) {
        System.out.print("\n\n\n\n" + surveyId + "\n\n\n\n");
        return "questioncreate";
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

        Question q = new McQuestion("I am Question", QuestionType.MULTIPLE_CHOICE);

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
