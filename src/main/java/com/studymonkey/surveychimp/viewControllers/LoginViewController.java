package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "login")
public class LoginViewController {

    public final AccountRepository repository;

    LoginViewController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("authenticate")
    public String login(@ModelAttribute Account account, Model model) {
        if(repository.findByUsernameAndPassword(account.getUsername(), account.getHexPassword()) != null) {
            System.out.println("Success");
            return "index";
        } else {
            System.out.println("Failure");
            return "index";
        }
    }
}
