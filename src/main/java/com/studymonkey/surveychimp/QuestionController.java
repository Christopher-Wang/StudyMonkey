package com.studymonkey.surveychimp;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class QuestionController {

    private final QuestionRepository repository;
    private final QuestionModelAssembler assembler;

    public QuestionController(QuestionRepository repository, QuestionModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/questions")
    CollectionModel<EntityModel<Question>> allQuestions() {
        List<EntityModel<Question>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(QuestionController.class).allQuestions()).withSelfRel());
    }

    @PostMapping("/questions")
    ResponseEntity<?> newQuestion(@RequestBody Question question) {
        EntityModel<Question> entityModel = assembler.toModel(repository.save(question));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/questions/{id}")
    EntityModel<Question> getQuestion(@PathVariable Long id) {
        Question question = repository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));

        return assembler.toModel(question);
    }

    @PutMapping("/questions/{id}")
    ResponseEntity<?> replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        Question updatedQuestion = repository.findById(id)
                .map(question -> {
                    question.setName(newQuestion.getName());
                    return repository.save(question);
                })
                .orElseGet(() -> {
                    newQuestion.setId(id);
                    return repository.save(newQuestion);
                });

        // question object iis wrap using the QuestionModelAssembler to an EnentyModel
        EntityModel<Question> entityModel = assembler.toModel(updatedQuestion);

        return ResponseEntity // getRequiredLink will get the linked created by the QuestiionModelAssembler with a SELF rel
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/questions/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
