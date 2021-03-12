package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "signup")
public class SignupViewController {

    public final AccountRepository repository;

    SignupViewController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String signup(Model model) {
        model.addAttribute("account", new Account()); // pass to the html template
        return "signup";
    }

    @ResponseBody
    @PostMapping("createAccount")
    public Account create(@ModelAttribute Account account) {
        return repository.save(account);
    }
}
