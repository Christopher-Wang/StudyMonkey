package com.studymonkey.surveychimp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Question {

    private @Id @GeneratedValue Long id;
    private String name;

    public Question() {}

    public Question(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(this.id, this.name);
//    }

    // useful for logging
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
