package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.survey.SurveyDaoImpl;
import com.studymonkey.surveychimp.dao.survey.SurveyQuestionsDao;
import com.studymonkey.surveychimp.dao.survey.SurveyQuestionsImpl;
import com.studymonkey.surveychimp.entity.survey.CompletedSurvey;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> insertQuestion(SurveyQuestions survey) {
        return surveyQuestionsDao.insertSurvey(survey);
    }

}
