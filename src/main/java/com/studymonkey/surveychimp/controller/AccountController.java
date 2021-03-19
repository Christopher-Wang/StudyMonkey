package com.studymonkey.surveychimp.controller;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import com.studymonkey.surveychimp.security.Security;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


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
    public ResponseEntity authenticate(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
        Account account = null;
        try {
            account = repository.findByUsernameAndPassword(username, Security.toHexString(Security.getSHA(password)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(account == null) {
            return new ResponseEntity<>("Authentication Failed", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(HttpStatus.OK);

    }
}






