package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.survey.SurveyDao;
import com.studymonkey.surveychimp.dao.survey.SurveyDaoImpl;
import com.studymonkey.surveychimp.dao.user.UserDaoImpl;
import com.studymonkey.surveychimp.entity.User;
import com.studymonkey.surveychimp.entity.survey.Survey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SurveyService {

    SurveyDao surveyDao;

    public SurveyService(SurveyDaoImpl surveyDao) {
        this.surveyDao = surveyDao;
    }

    public List<Survey> findAll() {
        return surveyDao.findAll();
    }

    public Survey findSurvey(int surveyId) {
        return surveyDao.findSurvey(surveyId);
    }

    public void insertSurvey(Survey survey) {
        surveyDao.insertSurvey(survey);
    }

    public void updateSurvey(Survey survey) {
        surveyDao.updateSurvey(survey);
    }

    public void executeUpdateSurvey(Survey survey) {
        surveyDao.executeUpdateSurvey(survey);
    }

    public void deleteSurvey(int surveyId) {
        surveyDao.deleteSurvey(surveyId);
    }
}
