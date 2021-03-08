package com.studymonkey.surveychimp.dao.survey;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.survey.CompletedSurvey;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import com.studymonkey.surveychimp.mapper.CompletedSurveyRowMapper;
import com.studymonkey.surveychimp.mapper.SurveyQuestionsRowMapper;
import com.studymonkey.surveychimp.mapper.SurveyQuestionsRowsMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SurveyQuestionsImpl implements SurveyQuestionsDao {

    private NamedParameterJdbcTemplate template;

    public SurveyQuestionsImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<SurveyQuestions> findAll() {
        return template.query("select * from survey left join question q on survey.id = q.survey_id", new SurveyQuestionsRowsMapper());
    }

    @Override
    public SurveyQuestions getSurvey(int surveyId) {
        final String sql = "select * from survey left join question q on survey.id = q.survey_id where id=:surveyId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId);

        return template.query(sql, param, new SurveyQuestionsRowMapper());
    }

    @Override
    public CompletedSurvey getSurveyAndAnswers(int surveyId, int userId) {
        final String sql = "select * from ((survey left join question q on survey.id = q.survey_id) LEFT JOIN question_answer on survey.id = question_answer.survey_id) WHERE client_id=:userId and survey.id =:surveyId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId)
                .addValue("userId", userId);

        return template.queryForObject(sql,param, new CompletedSurveyRowMapper());
    }

    @Override
    public void insertSurvey(SurveyQuestions survey) {
        final String sql = "insert into survey(name,description,survey_status_id) values(:name,:description,:status)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", survey.getName())
                .addValue("description", survey.getDescription())
                .addValue("status", survey.getStatus());
        template.update(sql,param, holder);

        for(Question question : survey.getQuestions()) {
            final String sql2 = "insert into question(survey_id, question_order, question, question_type) values(:surveyId,:questionOrder,:question,:questionType)";

            KeyHolder holder2 = new GeneratedKeyHolder();
            SqlParameterSource param2 = new MapSqlParameterSource()
                    .addValue("surveyId", question.getSurveyId())
                    .addValue("questionOrder", question.getQuestionOrder())
                    .addValue("question", question.getQuestion())
                    .addValue("questionType", QuestionType.MULTIPLE_CHOICE);
            template.update(sql2,param, holder);
        }
    }

}
