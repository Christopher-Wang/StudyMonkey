package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "surveyV2")
public class SurveyViewController {

    private final SurveyRepository surveyRepository;
    private final AccountRepository accountRepository;

    public SurveyViewController(SurveyRepository repository, AccountRepository accountRepository) {
        this.surveyRepository = repository;
        this.accountRepository = accountRepository;
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
    public String surveyEdit (@RequestParam(value = "surveyId") Long surveyId,
                              @CookieValue(value = "accountId", defaultValue = "0") String accountId,
                              Model model) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if(survey.isPresent()) {
            Survey s = survey.get();
            if (s.getCreator() != null){
                if (s.getCreator().getId().equals(Long.parseLong(accountId))){
                    model.addAttribute("survey", s);
                    return "surveyedit";
                }
            }
        }
        return "invalidpermission";
    }

    /**
     * A post method to edit the survey
     * @param survey the modifed survey
     * @return the view to the home page if successful or the view to the error page otherwise
     */
    @PostMapping("survey")
    public String surveyEdit (@ModelAttribute("survey") Survey survey, Model model) {
        if (!survey.getName().equals("") && !survey.getDescription().equals("")) {
            surveyRepository.updateSurvey(survey.getName(),survey.getDescription(),survey.getStatusEnum(), survey.getId());
            return getSurveyList(model);
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
        List<Survey> surveyList = surveyRepository.findAll();
        model.addAttribute("surveyList", surveyList);
        return "surveycatalogue";
    }


    /**
     * A post method to add the created value to the repository
     * @param survey the survey being saved
     * @return the view to the home page if successful or the view to the error page otherwise
     */
    @PostMapping("surveycreation")
    public String surveyCreation (@ModelAttribute("survey") Survey survey,
                                  @CookieValue(value = "accountId", defaultValue = "0") String accountId) {
        if (!survey.getName().equals("") && !survey.getDescription().equals("")) {
            Account creator = accountRepository.findById(Long.parseLong(accountId));
            if (creator != null) {
                survey.setCreator(creator);
                surveyRepository.save(survey);
                return "index";
            }
        }
        return "invalidpermission";

    }
}
