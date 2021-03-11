package com.studymonkey.surveychimp.entity;

/**
 * This class stores the data of a user that uses the application.
 *
 * Note: the userID is auto incremented by the database, therefore it is not used
 * when inserting the record into the database.
 */
public class User {

    private int id;
    private String userName;
    private String userEmail;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
