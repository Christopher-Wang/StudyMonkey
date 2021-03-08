package com.studymonkey.surveychimp;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class QuestionModelAssembler implements RepresentationModelAssembler<Question, EntityModel<Question>> {

    @SuppressWarnings("NullableProblems")
    @Override
    public EntityModel<Question> toModel(Question question) {

        return EntityModel.of(question,
                linkTo(methodOn(QuestionController.class).getQuestion(question.getId())).withSelfRel(), // "withSelRel" flag as a self
                linkTo(methodOn(QuestionController.class).allQuestions()).withRel("employees"));
    }
}
