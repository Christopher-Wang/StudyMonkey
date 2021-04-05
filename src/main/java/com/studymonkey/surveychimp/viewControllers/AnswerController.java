package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.answers.*;
import com.studymonkey.surveychimp.entity.questions.McOption;
import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.repositories.AnswerRepository;
import com.studymonkey.surveychimp.repositories.McOptionRepository;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
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
    private final McOptionRepository mcOptionRepository;

    public AnswerController(QuestionRepository questionRepository,
                            AnswerRepository answerRepository,
                            McOptionRepository mcOptionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.mcOptionRepository = mcOptionRepository;
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
    Example: curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/answer/questionAnswersJSON/2
     */
    @GetMapping("questionAnswersJSON/{questionId}")
    @ResponseBody
    public List<Answer> getQuestionAnswersJSON(@PathVariable long questionId) {
        Question q = this.questionRepository.findById(questionId);

        if (q == null) {
            return null;
        }

        List<Answer> answers= q.getAnswers();
        return answers;
    }

    @GetMapping("/questionAnswers/{questionId}")
    public String getQuestionAnswers(@PathVariable long questionId, Model model) {
        Question q = this.questionRepository.findById(questionId);

        if (q == null) {
            System.out.println("Question does not exist");
            return null;
        }

        List<Answer> answers = q.getAnswers();
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", q);
        model.addAttribute("answers", answers);

        if (q.getQuestionType() == QuestionType.TEXT) {

            return "textanswers";
        }
        else if (q.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
            return "mcanswers";
        }
        else if (q.getQuestionType() == QuestionType.RANGE) {
            return "rangeanswers";
        }

        return "error";
    }

    /*
    Example: curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/answer/rangeAnswer/barchart/2
    */
    @ResponseBody
    @GetMapping("/rangeAnswer/barchart/{questionId}")
    public List<Answer> getQuestionAnswersRangeBarchart(@PathVariable long questionId, Model model) {
        Question q = this.questionRepository.findById(questionId);

        if (q == null) {
            System.out.println("Question does not exist");
            return null;
        }

        return q.getAnswers();
    }

    @GetMapping
    public String answerForm(@RequestParam(value = "questionId") Long questionId, Model model) {
        Question q = this.questionRepository.findById(questionId).get();

        if (q.getQuestionType() == QuestionType.TEXT) {
            model.addAttribute("question", q);
            return "textanswercreate";
        }
        else if (q.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
            Question question = (McQuestion) this.questionRepository.findById(questionId).get();

            model.addAttribute("question", q);
            model.addAttribute("mcOptions", ((McQuestion) question).getMcOption());

            return "mcAnswerCreate";
        }
        else if (q.getQuestionType() == QuestionType.RANGE) {
            model.addAttribute("question", q);
            return "rangeAnswerCreate";
        }

        return "The question type couldn't be identified";
    }

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

    @PostMapping("/mcAnswer/{questionId}")
    public String postMcAnswer(@PathVariable long questionId, @RequestParam(value = "mcOptionId") long mcOptionId, Model model) {

        Question q = this.questionRepository.findById(questionId);
        McOption mcOption = this.mcOptionRepository.findById(mcOptionId);

        McAnswer mcAns = new McAnswer(AnswerType.MULTIPLE_CHOICE, mcOption);

        mcAns.setQuestion(q);
        q.addAnswer(mcAns);

        this.answerRepository.save(mcAns);
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

    @PostMapping("/rangeAnswer/{questionId}")
    public String postTextAnswer(@PathVariable long questionId, @RequestParam(value = "rangeAnswer") int answerNum, Model model) {
        Question q = this.questionRepository.findById(questionId);

        RangeAnswer rangeAns = new RangeAnswer(AnswerType.RANGE, answerNum);

        rangeAns.setQuestion(q);
        q.addAnswer(rangeAns);

        this.answerRepository.save(rangeAns);
        this.questionRepository.save(q);

        Survey survey = q.getSurvey();
        if (survey != null) {
            List<Question> questionList = survey.getQuestions();
            model.addAttribute("surveyId", survey.getId());
            model.addAttribute("questionList", questionList);
            return "questioncatalogue";
        } else {
            return "error";
        }
    }
}
