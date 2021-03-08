package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.questions.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        Question question = new Question();
        question.setSurveyId(resultSet.getInt("survey_id"));
        question.setQuestionOrder(resultSet.getInt("question_order"));
        question.setQuestion(resultSet.getString("question"));

        return question;
    }
}
