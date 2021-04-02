package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "signup")
public class SignupViewController {

    private final AccountRepository repository;

    SignupViewController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String signup(Model model) {
        model.addAttribute("account", new Account()); // pass to the html template
        return "signup";
    }

    @PostMapping("createAccount")
    public String create(@ModelAttribute Account account) {
        if (!account.getUsername().equals("") && !account.getEmail().equals("") && !account.getHexPassword().equals(""))
            return "index";
        else
            throw new RuntimeException();
    }
}
