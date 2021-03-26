package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.questions.McOption;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import com.studymonkey.surveychimp.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "surveyV2")
public class SurveyViewController {

    private final SurveyRepository repository;

    public SurveyViewController(SurveyRepository repository) {
        this.repository = repository;
    }

    /**
     * Example Request: http://localhost:8080/surveyV2
     * @param model the model of the system
     * @return the view for creating a survey
     */
    @GetMapping
    public String surveyForm(Model model) {
        model.addAttribute("survey", new Survey());
        return "surveycreate";
    }

    /**
     * A get method to get the edit page
     * @param surveyId the survey being edited
     * @return the view to the home page if successful or the view to the error page otherwise
     */
    @GetMapping("modifySurvey")
    public String surveyEdit (@RequestParam(value = "surveyId") Long surveyId, Model model) {
        Optional<Survey> survey = repository.findById(surveyId);
        if(survey.isPresent()) {
            model.addAttribute("survey", survey.get());
            return "surveyedit";
        } else {
            return "error";
        }
    }

    /**
     * A put method to edit the survey
     * @param survey the survey being modify
     * @return the view to the home page if successful or the view to the error page otherwise
     */
    @PostMapping("survey")
    public String surveyEdit (@ModelAttribute("survey") Survey survey) {
        if (!survey.getName().equals("") && !survey.getDescription().equals("")) {
            repository.updateSurvey(survey.getName(),survey.getDescription(),survey.getStatusEnum(), survey.getId());
            return "index";
        } else {
            return "error";
        }
    }

    /**
     * Example Request: http://localhost:8080/surveyV2/surveyList
     * @param model the model of the system
     * @return the view for getting all the surveys
     */
    @GetMapping(value = "surveyList")
    public String getSurveyList(Model model) {
        List<Survey> surveyList = repository.findAll();
        model.addAttribute("surveyList", surveyList);
        return "surveycatalogue";
    }


    /**
     * A post method to add the created value to the repository
     * @param survey the survey being saved
     * @return the view to the home page if successful or the view to the error page otherwise
     */
    @PostMapping("surveycreation")
    public String surveyCreation (@ModelAttribute("survey") Survey survey) {
        if (!survey.getName().equals("") && !survey.getDescription().equals("")) {
            repository.save(survey);
            return "index";
        } else {
            return "error";
        }
    }
}
