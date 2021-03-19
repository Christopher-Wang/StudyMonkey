package com.studymonkey.surveychimp.entity.questions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class McQuestion extends Question {

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "question")
    private List<McOption> mcOption;

    public McQuestion(){
        this.mcOption = new ArrayList<McOption>();
    }

    public McQuestion(String question, QuestionType questionType){
        super(question, questionType);
        this.mcOption = new ArrayList<McOption>();
    }

    public List<McOption> getMcOption() {
        return mcOption;
    }

    public void setMcOption(List<McOption> mcOption) {
        this.mcOption = mcOption;
    }

}
