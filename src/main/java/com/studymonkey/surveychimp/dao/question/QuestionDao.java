package com.studymonkey.surveychimp.dao.question;

import com.studymonkey.surveychimp.entity.questions.Question;

import java.util.List;
import java.util.Map;

public interface QuestionDao {
    public List<Question> findAll();
    public List<Map<String, Object>> insertQuestion(Question question);
    public List<Question> findAllQuestionsInSurvey(int surveyId);
    public Question findQuestion(int surveyId, int questionOrder);
    public void updateQuestion(Question question);
    public void executeUpdateQuestion(Question question);
    public void deleteQuestion(int surveyId, int questionOrder);
}
