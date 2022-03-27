package com.tejnal.springapps.controller;

import com.tejnal.springapps.entity.User;
import com.tejnal.springapps.model.UserResponse;
import com.tejnal.springapps.model.service.GitHubLookupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    GitHubLookupServiceImpl gitHubLookupService;


    @GetMapping("/results")
    public HttpEntity<UserResponse> getAllUserResults()  {

        try {
        // Kick of multiple, asynchronous lookups
        CompletableFuture<UserResponse> test1 = gitHubLookupService.findByUser("PivotalSoftware");
        CompletableFuture<UserResponse> test2 = gitHubLookupService.findByUser("CloudFoundry");
        CompletableFuture<UserResponse> test3 = gitHubLookupService.findByUser("Spring-Projects");
        CompletableFuture<UserResponse> test4 = gitHubLookupService.findByUser("tejnal");

        CompletableFuture.allOf(test1, test2, test3, test4).join();

        } catch(Exception e) {

            log.warn("Something went wrong ");
            try {
                throw e.getCause();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }
        return new ResponseEntity<>(OK);





    }
}
