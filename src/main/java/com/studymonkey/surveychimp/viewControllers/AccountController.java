package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import com.studymonkey.surveychimp.security.Security;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity authenticate(@RequestParam(name="username") String username,
                                       @RequestParam(name="password") String password,
                                       HttpServletResponse response) throws NoSuchAlgorithmException {
        if(username == null || password == null) {
            return ResponseEntity.badRequest().build();
        }

        Account account = null;
        try {
            account = repository.findByUsernameAndPassword(username, Security.getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(account == null) {
            return new ResponseEntity<>("Authentication Failed", HttpStatus.NO_CONTENT);
        }
        Cookie accountCookie = new Cookie("accountId", Long.toString(account.getId()));
        response.addCookie(accountCookie);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}






