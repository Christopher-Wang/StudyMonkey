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

        int rowCount = 0;
        while(resultSet.next()) {

            //Set the question text
            if(rowCount == 0) {
                question.setSurveyId(resultSet.getInt("survey_id"));
                question.setQuestionOrder(resultSet.getInt("question_order"));
                question.setQuestion(resultSet.getString(2));
                question.setQuestionType(QuestionType.getQuestionTypeFromValue(resultSet.getInt("question_type")));
                question.setOptionId(resultSet.getInt("id"));
            }

            //Gather the MC options
            String option = resultSet.getString(7);

            mcOptions.add(option);
            rowCount++;
        }
        question.setMcOption(mcOptions);

        return question;
    }
}
