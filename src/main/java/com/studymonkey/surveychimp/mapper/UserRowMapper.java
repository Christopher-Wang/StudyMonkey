package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserID(resultSet.getString("id"));
        user.setUserName(resultSet.getString("name"));
        user.setUserEmail(resultSet.getString("email"));

        return user;
    }
}
