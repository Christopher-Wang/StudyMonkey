package com.studymonkey.surveychimp.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupViewController {
    @GetMapping("signup") // map request to method
    public String signup(Model model) {
        return "signup";
    }
}
