package com.studymonkey.surveychimp.dao.user;

import com.studymonkey.surveychimp.entity.User;
import com.studymonkey.surveychimp.mapper.UserRowMapper;
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
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate template;

    public UserDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> findAll() {
        return template.query("select * from client", new UserRowMapper());
    }

    @Override
    public User findUser(int userId) {
        final String sql = "select * from client where id=:id";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId);

        return template.queryForObject(sql,param, new UserRowMapper());
    }

    @Override
    public void insertUser(User user) {
        final String sql = "insert into client(name, email) values(:userName,:userEmail)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userName", user.getUserName())
                .addValue("userEmail", user.getUserEmail());
        template.update(sql,param, holder);

    }

    @Override
    public void updateUser(User user) {
        final String sql = "update client set name=:userName, email=:userEmail where id=:userId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", user.getId())
                .addValue("userName", user.getUserName())
                .addValue("userEmail", user.getUserEmail());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateUser(User user) {
        final String sql = "update client set name=:userName, email=:userEmail where id=:userId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId", user.getId());
        map.put("userName", user.getUserName());
        map.put("userEmail", user.getUserEmail());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteUser(int userId) {
        final String sql = "delete from client where id=:userId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId", userId);

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
