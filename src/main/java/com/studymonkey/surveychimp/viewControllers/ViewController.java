package com.studymonkey.surveychimp.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/") // map request to method
    public String DefaultPage() {
        return "redirect:/login";
    }

    @GetMapping("home") // map request to method
    public String homePage(Model model) {
        return "index";
    }

    @GetMapping("about") // map request to method
    public String aboutPage(Model model) {
        return "about";
    }
}
