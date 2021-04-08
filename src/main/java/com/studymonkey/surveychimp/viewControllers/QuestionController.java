package com.studymonkey.surveychimp.viewControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymonkey.surveychimp.entity.questions.*;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.wrapper.McQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.RangeQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.TextQuestionWrapper;
import com.studymonkey.surveychimp.repositories.McOptionRepository;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "question")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final McOptionRepository mcOptionRepository;
    private final ObjectMapper objectMapper;

    public QuestionController(QuestionRepository questionRepository,
                              SurveyRepository surveyRepository,
                              McOptionRepository mcOptionRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
        this.mcOptionRepository = mcOptionRepository;
        this.objectMapper = new ObjectMapper();
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
        return "questioncreate";
    }

    @PostMapping(params={"questionType=TEXT"})
    public String textQuestionSubmit(@RequestParam QuestionType questionType,
                       @RequestBody String questionJSON,
                       Model model){
        try{
            TextQuestionWrapper wrapper = objectMapper.readValue(questionJSON, TextQuestionWrapper.class);
            Survey s = this.surveyRepository.findById(wrapper.getSurveyId());
            TextQuestion q = new TextQuestion(wrapper.getQuestion(), wrapper.getQuestionType());
            if (s == null) return "error";
            q.setSurvey(s);
            s.addQuestion(q);
            this.questionRepository.save(q);
            this.surveyRepository.save(s);
            model.addAttribute("surveyId", wrapper.getSurveyId());
            return "questioncreate";
        } catch (Exception e) {
            System.out.println("Exception raised: " + e);
        }

        return "error";
    }

    @PostMapping(params={"questionType=MULTIPLE_CHOICE"})
    public String mcQuestionSubmit(@RequestParam QuestionType questionType,
                        @RequestBody String questionJSON,
                        Model model){
        try{
            McQuestionWrapper wrapper = objectMapper.readValue(questionJSON, McQuestionWrapper.class);
            Survey s = this.surveyRepository.findById(wrapper.getSurveyId());
            McQuestion q = new McQuestion(wrapper.getQuestion(), wrapper.getQuestionType());
            if (s == null) return "error";

            ArrayList<McOption> mcOptions = new ArrayList<McOption>();
            for (String option: wrapper.getOptions()) {
                McOption o = new McOption(option);
                o.setQuestion(q);
                mcOptions.add(o);
            }
            q.setMcOption(mcOptions);
            q.setSurvey(s);
            s.addQuestion(q);
            this.questionRepository.save(q);
            mcOptions.forEach(o -> this.mcOptionRepository.save(o));
            this.surveyRepository.save(s);
            model.addAttribute("surveyId", wrapper.getSurveyId());
            return "questioncreate";
        } catch (Exception e) {
            System.out.println("Exception raised: " + e);
        }
        return "error";
    }

    @PostMapping(params={"questionType=RANGE"})
    public String rangeQuestionSubmit(@RequestParam QuestionType questionType,
                                     @RequestBody String questionJSON,
                                     Model model){
        try{
            RangeQuestionWrapper wrapper = objectMapper.readValue(questionJSON, RangeQuestionWrapper.class);
            System.out.println(wrapper.getMax() + "" + wrapper.getMin());
            Survey s = this.surveyRepository.findById(wrapper.getSurveyId());
            RangeQuestion q = new RangeQuestion(wrapper.getMin(), wrapper.getMax(), wrapper.getQuestion(), wrapper.getQuestionType());
            if (s == null) return "error";
            q.setSurvey(s);
            s.addQuestion(q);
            this.questionRepository.save(q);
            this.surveyRepository.save(s);
            model.addAttribute("surveyId", wrapper.getSurveyId());
            return "questioncreate";
        } catch (Exception e) {
            System.out.println("Exception raised: " + e);
        }
        return "error";
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
}
