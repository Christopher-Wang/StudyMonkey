package com.studymonkey.surveychimp.dao.answer;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import java.util.List;
import java.util.Map;

public interface SurveyAnswerDao {
    public List<Answer> findAll();
    public List<Answer> findAllAnswersOfUserForSurvey(int surveyId, int userId);
    public List<Answer> findAllAnswersForSurvey(int surveyId);
    public Answer getAnswer(int surveyId, int userId, int questionOrder);
    public List<Map<String, Object>> insertMcAnswer(McAnswer answer);
    public void insertTextAnswer(TextAnswer answer);
}
