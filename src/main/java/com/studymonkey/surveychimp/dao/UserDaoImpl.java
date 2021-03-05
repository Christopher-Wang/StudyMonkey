package com.studymonkey.surveychimp.dao;

import com.studymonkey.surveychimp.entity.User;
import com.studymonkey.surveychimp.mapper.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    public UserDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    NamedParameterJdbcTemplate template;

    @Override
    public List<User> findAll() {
        return template.query("select * from user", new UserRowMapper());
    }

    @Override
    public void insertUser(User emp) {
        final String sql = "insert into employee(employeeId, employeeName , employeeAddress,employeeEmail) values(:employeeId,:employeeName,:employeeEmail,:employeeAddress)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", emp.getUserID())
                .addValue("userName", emp.getUserName())
                .addValue("userEmail", emp.getUserEmail());
        template.update(sql,param, holder);

    }

    @Override
    public void updateUser(User emp) {
        final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", emp.getUserID())
                .addValue("userName", emp.getUserName())
                .addValue("userEmail", emp.getUserEmail());
        template.update(sql,param, holder);

    }

    @Override
    public void executeUpdateEmployee(User emp) {
        final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId", emp.getUserID());
        map.put("userName", emp.getUserName());
        map.put("userEmail", emp.getUserEmail());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteUser(User emp) {
        final String sql = "delete from employee where employeeId=:employeeId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("employeeId", emp.getUserID());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
