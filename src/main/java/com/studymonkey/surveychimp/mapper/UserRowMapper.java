package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User emp = new User();
        emp.setUserID(resultSet.getString("userId"));
        emp.setUserName(resultSet.getString("userName"));
        emp.setUserEmail(resultSet.getString("userEmail"));

        return emp;
    }
}
