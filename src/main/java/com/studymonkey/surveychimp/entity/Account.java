package com.studymonkey.surveychimp.entity;

import javax.persistence.*;

/**
 * This class stores the data of a user that uses the application.
 *
 * Note: the userID is auto incremented by the database, therefore it is not used
 * when inserting the record into the database.
 */
@Entity(name="Account")
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String userName;
    private String userEmail;

    public Account() { }

    public Account(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

