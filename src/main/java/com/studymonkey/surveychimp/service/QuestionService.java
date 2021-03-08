package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.question.QuestionDao;
import com.studymonkey.surveychimp.dao.question.QuestionDaoImpl;
import com.studymonkey.surveychimp.dao.survey.SurveyDao;
import com.studymonkey.surveychimp.dao.user.UserDaoImpl;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.survey.Survey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class QuestionService {

    QuestionDao questionDao;

    public QuestionService(QuestionDaoImpl questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> findAll() {
        return questionDao.findAll();
    }

    public Question findQuestion(int surveryId, int questionOrder) {
        return questionDao.findQuestion(surveryId,questionOrder);
    }

    public void insertQuestion(Question question) {
        questionDao.insertQuestion(question);
    }

    public void updateQuestion(Question question) {
        questionDao.updateQuestion(question);
    }

    public void executeUpdateQuestion(Question question) {
        questionDao.executeUpdateQuestion(question);
    }

    public void deleteQuestion(int surveryId, int questionOrder) {
        questionDao.deleteQuestion(surveryId,questionOrder);
    }
}
