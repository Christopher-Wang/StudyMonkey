package com.studymonkey.surveychimp.dao.answer;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.mapper.SurveyAnswerRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SurveyAnswerDaoImpl implements SurveyAnswerDao {

    private NamedParameterJdbcTemplate template;

    public SurveyAnswerDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Answer> findAll() {
        return template.query("select * from question_answer", new SurveyAnswerRowMapper());
    }

    @Override
    public List<Answer> findAllAnswersForSurvey(int surveyId, int userId) {
        final String sql = "select * from question_answer where survey_id=:surveyId and client_id=:userId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId)
                .addValue("userId", userId);

        return template.query(sql,param, new SurveyAnswerRowMapper());
    }

    @Override
    public Answer getAnswer(int surveyId, int userId, int questionOrder) {
        final String sql = "select * from question_answer where survey_id=:surveyId and client_id=:userId and question_order=:questionOrder";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId)
                .addValue("userId", userId)
                .addValue("questionOrder", questionOrder);

        return template.queryForObject(sql,param, new SurveyAnswerRowMapper());
    }

    @Override
    public void insertMcAnswer(McAnswer answer) {
        final String sql = "insert into question_answer(survey_id, question_order, client_id, mc_option_id) values(:surveyId,:questionOrder,:question,:questionType)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", answer.getSurveyId())
                .addValue("questionOrder", answer.getQuestionOrder())
                .addValue("userId", answer.getUserId())
                .addValue("answer", answer.getMcOptionIdAnswer());
        template.update(sql,param, holder);
    }

    @Override
    public void insertTextAnswer(TextAnswer answer) {
        final String sql = "insert into question_answer(survey_id, question_order, client_id, text_answer) values(:surveyId,:questionOrder,:question,:questionType)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", answer.getSurveyId())
                .addValue("questionOrder", answer.getQuestionOrder())
                .addValue("userId", answer.getUserId())
                .addValue("answer", answer.getQuestionAnswer());
        template.update(sql,param, holder);
    }
}
