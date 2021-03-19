package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.survey.CompletedSurvey;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompletedSurveyRowMapper implements RowMapper<CompletedSurvey> {
    @Override
    public CompletedSurvey mapRow(ResultSet resultSet, int i) throws SQLException {
        CompletedSurvey survey = new CompletedSurvey();
        List<Question> questions = new ArrayList<Question>();
        List<Answer> answers = new ArrayList<Answer>();

        int rowCount = 0;
        while(resultSet.next()) {

            if(rowCount == 0) {
                survey.setId(resultSet.getInt("id"));
                survey.setName(resultSet.getString("name"));
                survey.setDescription(resultSet.getString("description"));
                survey.setStatus(SurveyStatus.values()[resultSet.getInt("survey_status_id")]);
            }

            Question question = new Question();
            question.setQuestionOrder(resultSet.getInt("question_order"));
            question.setQuestion(resultSet.getString("question"));
            question.setQuestionType(QuestionType.values()[resultSet.getInt("question_type_id")]);

            Answer answer = null;
            if(question.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
                McAnswer mcAnswer = new McAnswer();
                mcAnswer.setMcOptionIdAnswer(resultSet.getInt("mc_option_id"));
                answer = mcAnswer;
            } else if(question.getQuestionType() == QuestionType.TEXT) {
                TextAnswer textAnswer = new TextAnswer();
                textAnswer.setQuestionAnswer(resultSet.getString("text_answer"));
                answer = textAnswer;
            }

            questions.add(question);
            answers.add(answer);

            rowCount++;
        }
        survey.setQuestions(questions);
        survey.setAnswers(answers);

        return survey;
    }
}
