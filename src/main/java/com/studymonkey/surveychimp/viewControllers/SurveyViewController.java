package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

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
     * @param model the model of the system
     * @return the view to the home page if successful or the view to the error page otherwise
     */

    @PostMapping("surveycreation")
    public String surveyCreation ( @ModelAttribute("survey") Survey survey) {
        System.out.println("name: "+ survey.getName() + " des: " + survey.getDescription() + " id: "+ survey.getId() + " status: "+ survey.getStatus());
        if (!survey.getName().equals("") && !survey.getDescription().equals("")) {
            repository.save(survey);
            return "index";
        } else {
            return "error";
        }
    }
}
