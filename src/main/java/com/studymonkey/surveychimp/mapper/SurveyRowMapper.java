package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyRowMapper implements RowMapper<Survey> {

    @Override
    public Survey mapRow(ResultSet resultSet, int i) throws SQLException {
        Survey survey = new Survey();
        survey.setSurveyId(resultSet.getInt("survey_id"));
        survey.setName(resultSet.getString("name"));
        survey.setDescription(resultSet.getString("description"));
        survey.setStatus(SurveyStatus.values()[resultSet.getInt("survey_status_id")]);

        return survey;
    }
}
