package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.question.McQuestionDao;
import com.studymonkey.surveychimp.dao.question.McQuestionDaoImpl;
import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SurveyMcQuestionService implements McQuestionDao {

    McQuestionDao mcQuestionsDao;

    public SurveyMcQuestionService(McQuestionDaoImpl mcQuestionsDao) {
        this.mcQuestionsDao = mcQuestionsDao;
    }

    @Override
    public List<McQuestion> findAll() {
        return mcQuestionsDao.findAll();
    }

    @Override
    public List<Map<String, Object>> insertMcQuestion(McQuestion question) {
         return mcQuestionsDao.insertMcQuestion(question);
    }

    @Override
    public Question findMcQuestion(int surveyId, int questionOrder) {
        return mcQuestionsDao.findMcQuestion(surveyId,questionOrder);
    }

    @Override
    public void updateMcQuestion(McQuestion question) {
        mcQuestionsDao.updateMcQuestion(question);
    }

    @Override
    public void executeUpdateMcQuestion(McQuestion question) {

    }

    @Override
    public void deleteMcQuestion(int surveyId, int questionOrder) {
        mcQuestionsDao.deleteMcQuestion(surveyId, questionOrder);
    }
}
