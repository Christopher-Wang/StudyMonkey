package com.studymonkey.surveychimp.dao.question;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.mapper.McQuestionRowMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class McQuestionDaoImpl implements McQuestionDao {

    private NamedParameterJdbcTemplate template;
    private QuestionDao questionDao;

    public McQuestionDaoImpl(NamedParameterJdbcTemplate template, QuestionDao questionDao) {
        this.template = template;
        this.questionDao = questionDao;
    }

    @Override
    public List<McQuestion> findAll() {
        return template.query("select * from question left join mc_option mo on question.survey_id = mo.survey_id ORDER BY survey_id, question_order", new McQuestionRowMapper());
    }

    @Override
    public List<Map<String, Object>> insertMcQuestion(McQuestion question) {
        List<Map<String, Object>> questionResult = questionDao.insertQuestion(question);
        List<Map<String, Object>> mcOptionResult = new ArrayList<>();

        for(int i=0; i < question.getMcOption().size();i++) {
            final String sql = "insert into mc_option(survey_id, question_order_id, question) values(:surveyId,:questionOrder,:question)";

            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("surveyId", question.getSurveyId())
                    .addValue("questionOrder", questionResult.get(0).get("question_order"))
                    .addValue("question", question.getMcOption().get(i));

            template.update(sql,param,holder);
            mcOptionResult.add(holder.getKeyList().get(0));
        }
        return mcOptionResult;
    }

    @Override
    public Question findMcQuestion(int surveyId, int questionOrder) {
        final String sql = "select * from question left join mc_option mo on question.survey_id = mo.survey_id and question.question_order = mo.question_order where survey_id=:surveyId AND question_order=:questionOrder";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId)
                .addValue("questionOrder", questionOrder);

        return template.queryForObject(sql,param, new McQuestionRowMapper());
    }

    @Override
    public void updateMcQuestion(McQuestion question) {
        //Update the question text
        this.questionDao.updateQuestion(question);

        //Update the MC options within the question
        final String optionSql = "update mc_option SET question=:question WHERE id=:optionId";

        KeyHolder optionHolder = new GeneratedKeyHolder();
        SqlParameterSource optionParam = new MapSqlParameterSource()
                .addValue("optionId", question.getOptionId())
                .addValue("question", question.getMcOption().get(0));

        template.update(optionSql,optionParam,optionHolder);
    }

    @Override
    public void executeUpdateMcQuestion(McQuestion question) {

    }

    @Override
    public void deleteMcQuestion(int surveyId, int questionOrder) {
        //Delete the MC options
        final String sql = "delete from mc_option WHERE survey_id=:surveyId AND question_order_id=:questionOrder";

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

        //Delete the question text
        this.questionDao.deleteQuestion(surveyId,questionOrder);
    }
}
