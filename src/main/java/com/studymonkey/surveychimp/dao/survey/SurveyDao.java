package com.studymonkey.surveychimp.dao.survey;

import com.studymonkey.surveychimp.entity.survey.Survey;

import java.util.List;
import java.util.Map;

public interface SurveyDao {
    public List<Survey> findAll();
    public Survey findSurvey(int surveyId);
    public List<Map<String, Object>> insertSurvey(Survey survey);
    public void updateSurvey(Survey survey);
    public void executeUpdateSurvey(Survey survey);
    public void deleteSurvey(int surveyId);
}
