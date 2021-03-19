package com.studymonkey.surveychimp.entity.controller;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/accounts")
public class AccountController {

    public final AccountRepository repository;

    AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    @ResponseBody
    public Account getUsers(@RequestParam(name="username") String username) {
        Account account = repository.findByUsername(username);
        return account;
    }
}






