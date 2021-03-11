package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class McQuestionRowMapper implements RowMapper<McQuestion> {

    @Override
    public McQuestion mapRow(ResultSet resultSet, int i) throws SQLException {
        McQuestion question = new McQuestion();
        List<String> mcOptions = new ArrayList<String>();

        // Without this, it will always skip the first row.
        if (resultSet.isFirst()) {
            //Set the question text
            question.setSurveyId(resultSet.getInt("survey_id"));
            question.setQuestionOrder(resultSet.getInt("question_order"));
            question.setQuestion(resultSet.getString(2));
            question.setQuestionType(QuestionType.getQuestionTypeFromValue(resultSet.getInt("question_type")));
            question.setOptionId(resultSet.getInt("id"));

            String option = resultSet.getString(8);
            mcOptions.add(option);
        }

        while(resultSet.next()) {
            //Gather the MC options
            String option = resultSet.getString(8);
            mcOptions.add(option);
        }
        question.setMcOption(mcOptions);
        return question;
    }
}
