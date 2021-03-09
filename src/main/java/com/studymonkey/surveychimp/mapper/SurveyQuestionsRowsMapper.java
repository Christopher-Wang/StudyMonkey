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
import java.util.HashMap;
import java.util.List;

public class SurveyQuestionsRowsMapper implements ResultSetExtractor<List<SurveyQuestions>> {

    @Override
    public List<SurveyQuestions> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        HashMap<Integer, SurveyQuestions> surveys = new HashMap<>();
        List<Question> questions = new ArrayList<Question>();

        SurveyQuestions survey = null;
        Integer surveyId = null;
        while (resultSet.next()) {


            if (!surveys.containsKey(resultSet.getInt("id"))) {
                surveys.put(surveyId, survey);
                surveyId = resultSet.getInt("id");
                survey = new SurveyQuestions();

                survey.setId(surveyId);
                survey.setName(resultSet.getString("name"));
                survey.setDescription(resultSet.getString("description"));
                survey.setStatus(SurveyStatus.values()[resultSet.getInt("survey_status_id")]);
            }

            Question question = new Question();
            question.setQuestionOrder(resultSet.getInt("question_order"));
            question.setQuestion(resultSet.getString("question"));
            question.setQuestionType(QuestionType.values()[resultSet.getInt("question_type_id")]);

            questions.add(question);
        }
        survey.setQuestions(questions);

        List<SurveyQuestions> surveyQuestionsList = new ArrayList<SurveyQuestions>();
        for (SurveyQuestions surveyQuestions : surveys.values()) {
            surveyQuestionsList.add(surveyQuestions);
        }

        return surveyQuestionsList;
    }

}
