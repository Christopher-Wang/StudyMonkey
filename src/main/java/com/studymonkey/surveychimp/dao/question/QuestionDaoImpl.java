package com.studymonkey.surveychimp.dao.question;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.mapper.QuestionRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionDaoImpl implements QuestionDao {

    private NamedParameterJdbcTemplate template;

    public QuestionDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Question> findAll() {
        return template.query("select * from question", new QuestionRowMapper());
    }

    @Override
    public List<Map<String, Object>> insertQuestion(Question question) {
        final String sql = "insert into question(survey_id, question, question_type) values(:surveyId,:question,:questionType)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", question.getSurveyId())
                .addValue("question", question.getQuestion())
                .addValue("questionType", question.getQuestionType().getValue());

        template.update(sql,param, holder);
        return holder.getKeyList();
    }

    @Override
    public List<Question> findAllQuestionsInSurvey(int surveyId) {
        final String sql = "select * from question where survey_id=:surveyId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId);

        return template.query(sql,param, new QuestionRowMapper());
    }

    @Override
    public Question findQuestion(int surveyId, int questionOrder) {
        final String sql = "select * from question where survey_id=:surveyId AND question_order=:questionOrder";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId)
                .addValue("questionOrder", questionOrder);

        return template.queryForObject(sql,param, new QuestionRowMapper());
    }

    @Override
    public List<Map<String, Object>> updateQuestion(Question question) {
        final String sql = "update question set question=:question, question_type=:questionType where survey_id=:surveyId and question_order=:questionOrder";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", question.getSurveyId())
                .addValue("questionOrder", question.getQuestionOrder())
                .addValue("question", question.getQuestion())
                .addValue("questionType", question.getQuestionType().getValue());
        template.update(sql,param, holder);

        return holder.getKeyList();
    }

    @Override
    public void executeUpdateQuestion(Question question) {
        final String sql = "update question set question=:question, question_type=:questionType where survey_id=:surveyId and question_order=:questionOrder";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("surveyId", question.getSurveyId());
        map.put("questionOrder", question.getQuestionOrder());
        map.put("question", question.getQuestion());
        map.put("questionType", QuestionType.MULTIPLE_CHOICE);

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteQuestion(int surveyId, int questionOrder) {
        final String sql = "delete from question where survey_id=:surveyId and question_order=:questionOrder";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("surveyId", surveyId);
        map.put("questionOrder", questionOrder);

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}


