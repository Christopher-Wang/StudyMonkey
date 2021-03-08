package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyQuestionsRowMapper implements ResultSetExtractor<SurveyQuestions> {

    @Override
    public SurveyQuestions extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        SurveyQuestions survey = new SurveyQuestions();
        List<Question> questions = new ArrayList<Question>();

        int rowCount = 0;
        while(resultSet.next()) {

            if(rowCount == 0) {
                survey.setSurveyId(resultSet.getInt("id"));
                survey.setName(resultSet.getString("name"));
                survey.setDescription(resultSet.getString("description"));
                survey.setStatus(SurveyStatus.values()[resultSet.getInt("survey_status_id")]);
            }

            Question question = new Question();
            question.setQuestionOrder(resultSet.getInt("question_order"));
            question.setQuestion(resultSet.getString("question"));
            question.setQuestionType(QuestionType.values()[resultSet.getInt("question_type_id")]);

            questions.add(question);
            rowCount++;
        }
        survey.setQuestions(questions);

        return survey;
    }

}
