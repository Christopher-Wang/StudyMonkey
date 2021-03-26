package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.repositories.MetricRepository;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "metrics")
public class MetricViewController {

    private final MetricRepository repository;
    private final QuestionRepository questionRepository;

    public MetricViewController(MetricRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public String getMetricsPage(Model model) {
        return "index";
    }

    @PostMapping
    public String computeMetrics(Model model) {
        return "index";
    }
}
