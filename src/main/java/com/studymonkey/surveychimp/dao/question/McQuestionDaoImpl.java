package com.studymonkey.surveychimp.dao.question;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.mapper.QuestionRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class McQuestionDaoImpl implements McQuestionDao {

    private NamedParameterJdbcTemplate template;
    private QuestionDao questionDao;

    public McQuestionDaoImpl(NamedParameterJdbcTemplate template, QuestionDao questionDao) {
        this.template = template;
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> findAll() {
        return template.query("select * from question left join mc_option mo on question.survey_id = mo.survey_id ORDER BY survey_id, question_order", new QuestionRowMapper());
    }

    @Override
    public void insertQuestion(McQuestion question) {
        this.questionDao.insertQuestion(question);

        for(int i=0; i < question.getMcOption().size();i++) {

        }

        final String sql = "insert into mc_option(survey_id, question_order, question) values(:surveyId,:questionOrder,:question,:questionType)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("surveyId", question.getSurveyId())
                .addValue("questionOrder", question.getQuestionOrder())
                .addValue("question", question.getMcOption().get(0));
    }

    @Override
    public Question findQuestion(int surveyId, int questionOrder) {
        return null;
    }

    @Override
    public void updateQuestion(McQuestion question) {

    }

    @Override
    public void executeUpdateQuestion(McQuestion question) {

    }

    @Override
    public void deleteQuestion(int surveyId, int questionOrder) {

    }
}
