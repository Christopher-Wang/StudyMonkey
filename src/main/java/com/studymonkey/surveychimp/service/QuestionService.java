package com.studymonkey.surveychimp.service;

import com.studymonkey.surveychimp.dao.question.QuestionDao;
import com.studymonkey.surveychimp.dao.question.QuestionDaoImpl;
import com.studymonkey.surveychimp.entity.questions.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> insertQuestion(Question question) {
        return questionDao.insertQuestion(question);
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

