package com.studymonkey.surveychimp.dao.question;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;

import java.util.List;

public interface McQuestionDao {
    public List<Question> findAll();
    public void insertQuestion(McQuestion question);
    public Question findQuestion(int surveyId, int questionOrder);
    public void updateQuestion(McQuestion question);
    public void executeUpdateQuestion(McQuestion question);
    public void deleteQuestion(int surveyId, int questionOrder);
}
