package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.AnswerType;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.repositories.AnswerRepository;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "answer")
public class AnswerController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public AnswerController(QuestionRepository questionRepository,
                            AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    /*
    Example: curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/answer/answer/3
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Answer getAnswer(@PathVariable long id) {
        Answer ans = this.answerRepository.findById(id);
        return ans;
    }

    /*
    Example:
    curl -X POST http://localhost:8080/answer/textAnswer/2 -H 'Content-type:application/json' -d '{"answerType": "TEXT", "questionAnswer": "my ANSWER"}'
     */
    @PostMapping("/textAnswer/{questionId}")
    @ResponseBody
    public Question postTextAnswer(@PathVariable long questionId, @RequestBody TextAnswer ans) {
        Question q = this.questionRepository.findById(questionId);

        ans.setQuestion(q);
        q.addAnswer(ans);

        this.answerRepository.save(ans);
        this.questionRepository.save(q);

        return q;
    }

    /*
    curl -X POST http://localhost:8080/answer/McAnswer/2 -H 'Content-type:application/json' -d '{"answerType": "MULTIPLE_CHOICE", "mcOptionIdAnswer": 1}'
     */
    @PostMapping("/McAnswer/{questionId}")
    @ResponseBody
    public Question postMcAnswer(@PathVariable long questionId, @RequestBody McAnswer ans) {
        Question q = this.questionRepository.findById(questionId);

        ans.setQuestion(q);
        q.addAnswer(ans);

        this.answerRepository.save(ans);
        this.questionRepository.save(q);

        return q;
    }

}
