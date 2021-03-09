package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.answer.SurveyAnswerDao;
import com.studymonkey.surveychimp.dao.answer.SurveyAnswerDaoImpl;
import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SurveyAnswerService {

    SurveyAnswerDao answerDao;

    public SurveyAnswerService(SurveyAnswerDaoImpl answerDao) {
        this.answerDao = answerDao;
    }

    public List<Answer> findAll() {
        return answerDao.findAll();
    }

    public List<Answer> findAllAnswersOfUserForSurvey(int surveyId, int userId) {
        return answerDao.findAllAnswersOfUserForSurvey(surveyId, userId);
    }

    public List<Answer> findAllAnswersForSurvey(int surveyId) {
        return answerDao.findAllAnswersForSurvey(surveyId);
    }

    public Answer getAnswer(int surveyId, int userId, int questionOrder) {
        return answerDao.getAnswer(surveyId, userId, questionOrder);
    }

    public void insertMcAnswer(McAnswer answer) {
        answerDao.insertMcAnswer(answer);
    }
    public void insertTextAnswer(TextAnswer answer) {
        answerDao.insertTextAnswer(answer);
    }
}
