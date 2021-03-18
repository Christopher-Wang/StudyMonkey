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

    @GetMapping
    public String surveyForm(Model model) {
        model.addAttribute("survey", new Survey(SurveyStatus.OPEN));
        return "surveycreate";
    }

    @GetMapping(value = "surveyList")
    public String getSurveyList(Model model) {
        List<Survey> surveyList = repository.findAll();
        model.addAttribute("surveyList", surveyList);
        return "surveycatalogue";
    }


    @PostMapping("surveycreation")
    public String surveyCreation(@ModelAttribute Survey survey, Model model) {
        if (!survey.getName().equals("") && !survey.getDescription().equals("")) {
            repository.save(survey);
            return "index";
        } else {
            return "error";
        }
    }
}
