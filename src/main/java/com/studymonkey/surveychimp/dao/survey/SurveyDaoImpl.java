package com.studymonkey.surveychimp.dao.survey;

import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.mapper.SurveyRowMapper;
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
public class SurveyDaoImpl implements SurveyDao {

    private NamedParameterJdbcTemplate template;

    public SurveyDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Survey> findAll() {
        return template.query("select * from survey", new SurveyRowMapper());
    }

    @Override
    public Survey findSurvey(int surveyId) {
        final String sql = "select * from survey where id=:surveyId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", surveyId);

        return template.queryForObject(sql,param, new SurveyRowMapper());
    }

    @Override
    public List<Map<String, Object>> insertSurvey(Survey survey) {
        final String sql = "insert into survey(name,description,survey_status_id) values(:name,:description,:status)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", survey.getName())
                .addValue("description", survey.getDescription())
                .addValue("status", survey.getStatus());
        template.update(sql,param, holder);

        return holder.getKeyList();
    }

    @Override
    public void updateSurvey(Survey survey) {
        final String sql = "update survey set name=:name, description=:description, survey_status_id=:status where id=:surveyId";

        System.out.println("UPDATING SURVEY");
        System.out.println("THE SURVEY ID " + survey.getId());

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", survey.getId())
                .addValue("name", survey.getName())
                .addValue("description", survey.getDescription())
                .addValue("status", survey.getStatus());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateSurvey(Survey survey) {
        final String sql = "update survey set name=:name, description=:description, survey_status_id=:status where id=:surveyId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("surveyId", survey.getId());
        map.put("name", survey.getName());
        map.put("description", survey.getDescription());
        map.put("status", survey.getStatus());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteSurvey(int surveyId) {
        final String sql = "delete from survey where id=:surveyId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("surveyId", surveyId);

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
