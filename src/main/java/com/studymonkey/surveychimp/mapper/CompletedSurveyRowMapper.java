package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.survey.CompletedSurvey;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompletedSurveyRowMapper implements RowMapper<CompletedSurvey> {
    @Override
    public CompletedSurvey mapRow(ResultSet resultSet, int i) throws SQLException {
        CompletedSurvey survey = new CompletedSurvey();
        survey.setId(resultSet.getInt("id"));
        survey.setName(resultSet.getString("name"));
        survey.setDescription(resultSet.getString("description"));
        //survey.setStatus(resultSet.getInt("survey_status_id"));
        //survey.setQuestions(resultSet.getObject(, Question));

        return survey;
    }
}
