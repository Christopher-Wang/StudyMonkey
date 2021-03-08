package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.survey.SurveyDaoImpl;
import com.studymonkey.surveychimp.dao.survey.SurveyQuestionsDao;
import com.studymonkey.surveychimp.dao.survey.SurveyQuestionsImpl;
import com.studymonkey.surveychimp.entity.survey.CompletedSurvey;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SurveyQuestionService {

    SurveyQuestionsDao surveyQuestionsDao;

    public SurveyQuestionService(SurveyQuestionsImpl surveyQuestionsDao) {
        this.surveyQuestionsDao = surveyQuestionsDao;
    }

    public List<SurveyQuestions> findAll() {
        return surveyQuestionsDao.findAll();
    }

    public SurveyQuestions findSurvey(int surveryId) {
        return surveyQuestionsDao.getSurvey(surveryId);
    }

    public CompletedSurvey findSurveyAndAnswers(int surveryId, int userId) {
        return surveyQuestionsDao.getSurveyAndAnswers(surveryId,userId);
    }

    public void insertQuestion(SurveyQuestions survey) {
        surveyQuestionsDao.insertSurvey(survey);
    }

}
