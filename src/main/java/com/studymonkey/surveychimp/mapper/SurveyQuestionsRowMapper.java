package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SurveyQuestionsRowMapper implements RowMapper<SurveyQuestions> {

    @Override
    public SurveyQuestions mapRow(ResultSet resultSet, int i) throws SQLException {
        SurveyQuestions survey = new SurveyQuestions();
        survey.setSurveyId(resultSet.getInt("id"));
        survey.setName(resultSet.getString("name"));
        survey.setDescription(resultSet.getString("description"));
        //survey.setStatus(resultSet.getInt("survey_status_id"));
        //survey.setQuestions(resultSet.getObject(, Question));

        return survey;
    }
}
