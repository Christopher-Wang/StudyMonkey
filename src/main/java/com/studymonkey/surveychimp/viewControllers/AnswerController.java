package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.AnswerType;
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
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;

    public AnswerController(QuestionRepository questionRepository,
                            SurveyRepository surveyRepository,
                            AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
        this.answerRepository = answerRepository;
    }


    /*
    Example: curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/answer/answer/3
     */
    @GetMapping("/answer/{id}")
    @ResponseBody
    public Answer getAnswer(@PathVariable long id) {
        Answer ans = this.answerRepository.findById(id);
        return ans;
    }
    
    /*
    Example:
    curl -X POST http://localhost:8080/answer/textAnswer/2 -H 'Content-type:application/json' -d '{"answerType": "TEXT", "questionAnswer": "my ANSWER"}'
     */
    @PostMapping("/textAnswer/{id}")
    @ResponseBody
    public Question postTextAnswer(@PathVariable long id, @RequestBody TextAnswer ans) {
        Question q = this.questionRepository.findById(id);

        ans.setQuestion(q);
        q.addAnswer(ans);

        this.answerRepository.save(ans);
        this.questionRepository.save(q);

        return q;
    }

}
