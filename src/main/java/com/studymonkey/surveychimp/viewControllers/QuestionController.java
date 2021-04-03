package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.questions.TextQuestion;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.wrapper.QuestionWrapper;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
     * @return the view for creating a question
     */
    @GetMapping
    public String questionForm(@RequestParam(value = "surveyId", required=false) Long surveyId, Model model) {
        if (surveyId == null) surveyId = 0L;
        model.addAttribute("surveyId", surveyId);
        return "temp";
    }

    @PostMapping(value="temp", params={"questionType=TEXT"})
    public String temp(@RequestParam QuestionType questionType, @RequestBody String questionJSON){
        System.out.println("\n\n\n\nTEXT\n\n\n\n");
        return "temp";
    }

    /**
     * Example Request: http://localhost:8080/question
     * @param model the model of the system
     * @return the view for listing questions
     */
    @GetMapping(value = "questionList")
    public String questionList(@RequestParam() Long surveyId, Model model) {
        Optional<Survey> result  = this.surveyRepository.findById(surveyId);
        if(result.isPresent()){
            Survey s = result.get();
            List<Question> questionList = s.getQuestions();
            model.addAttribute("surveyId", surveyId);
            model.addAttribute("questionList", questionList);
            return "questioncatalogue";
        }
        else {
            return "error";
        }
    }



    /**
     * Example Request: http://localhost:8080/question
     * @return the view for creating a question
     */
    @PostMapping
    public String submitQuestion(@ModelAttribute("questionWrapper") QuestionWrapper questionWrapper, Model model) {
//        Survey s = this.surveyRepository.findById(questionWrapper.getSurveyId());
//        Question q = questionWrapper.getQuestion();
//        QuestionType type = q.getQuestionType();
//        switch(type){
//            case TEXT:
//                if (s == null) return "error";
//                q = new TextQuestion(q.getQuestion(), q.getQuestionType());
//                break;
//            case MULTIPLE_CHOICE:
//                if (s == null) return "error";
//                q = new McQuestion(q.getQuestion(), q.getQuestionType());
//                break;
//            default:
//                if (s == null) return "error";
//        }
//        q.setSurvey(s);
//        s.addQuestion(q);
//        this.questionRepository.save(q);
//        this.surveyRepository.save(s);
//        model.addAttribute("questionWrapper", new QuestionWrapper(questionWrapper.getSurveyId(), new Question()));
        return "questioncreate";
    }
}
