package com.studymonkey.surveychimp.entity.questions;

import java.util.List;

public class McQuestion extends Question {
    private List<String> mcOption;

    public List<String> getMcOption() {
        return mcOption;
    }

    public void setMcOption(List<String> mcOption) {
        this.mcOption = mcOption;
    }
}
