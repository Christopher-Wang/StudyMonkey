package com.studymonkey.surveychimp.dao.question;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;

import java.util.List;
import java.util.Map;

public interface McQuestionDao {
    public List<McQuestion> findAll();
    public List<Map<String, Object>> insertMcQuestion(McQuestion question);
    public Question findMcQuestion(int surveyId, int questionOrder);
    public void updateMcQuestion(McQuestion question);
    public void executeUpdateMcQuestion(McQuestion question);
    public void deleteMcQuestion(int surveyId, int questionOrder);
}
