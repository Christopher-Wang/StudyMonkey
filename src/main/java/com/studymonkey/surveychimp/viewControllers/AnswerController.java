package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.AnswerType;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.wrapper.QuestionWrapper;
import com.studymonkey.surveychimp.repositories.AnswerRepository;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    Example: curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/answer/3
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Answer getAnswer(@PathVariable long id) {
        Answer ans = this.answerRepository.findById(id);
        return ans;
    }

    /*
    Example: curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/answer/questionAnswers/2
     */
    @GetMapping("questionAnswers/{questionId}")
    @ResponseBody
    public List<Answer> getQuestionAnswers(@PathVariable long questionId) {
        Question q = this.questionRepository.findById(questionId);

        if (q == null) {
            return null;
        }

        List<Answer> answers= q.getAnswers();
        return answers;
    }

    @GetMapping
    public String answerForm(@RequestParam(value = "questionId") Long questionId, Model model) {
        Question q = this.questionRepository.findById(questionId).get();

        model.addAttribute("question", q);
        return "answercreate";
    }

    // This may be referenced later in case we want to do SPA.
    /*
    Example:
    curl -X POST http://localhost:8080/answer/textAnswer/2 -H 'Content-type:application/json' -d '{"answerType": "TEXT", "questionAnswer": "my ANSWER"}'
     */
//    @PostMapping("/textAnswer/{questionId}")
//    @ResponseBody
//    public Question postTextAnswer(@PathVariable long questionId, @RequestBody TextAnswer ans) {
//        Question q = this.questionRepository.findById(questionId);
//
//        ans.setQuestion(q);
//        q.addAnswer(ans);
//
//        this.answerRepository.save(ans);
//        this.questionRepository.save(q);
//
//        return q;
//    }

    @PostMapping("/textAnswer/{questionId}")
    public String postTextAnswer(@PathVariable long questionId, @RequestParam(value = "questionAnswer") String ans, Model model) {
        Question q = this.questionRepository.findById(questionId);

        TextAnswer textAns = new TextAnswer(AnswerType.TEXT, ans);

        textAns.setQuestion(q);
        q.addAnswer(textAns);

        this.answerRepository.save(textAns);
        this.questionRepository.save(q);

        Survey survey  = q.getSurvey();
        if(survey != null){
            List<Question> questionList = survey.getQuestions();
            model.addAttribute("surveyId", survey.getId());
            model.addAttribute("questionList", questionList);
            return "questioncatalogue";
        }
        else {
            return "error";
        }
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
