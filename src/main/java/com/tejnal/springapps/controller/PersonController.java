package com.tejnal.springapps.controller;

import com.tejnal.springapps.model.PersonResponse;
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
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    GitHubLookupServiceImpl gitHubLookupService;

  @GetMapping("/results")
  public HttpEntity<PersonResponse> getAllUserResults() throws InterruptedException {
    // Kick of multiple, asynchronous lookups
    CompletableFuture<PersonResponse> test1 = gitHubLookupService.findByUser("PivotalSftware");
    CompletableFuture<PersonResponse> test2 = gitHubLookupService.findByUser("CloudFoundry");
    CompletableFuture<PersonResponse> test3 = gitHubLookupService.findByUser("Spring-Projects");
    CompletableFuture<PersonResponse> test4 = gitHubLookupService.findByUser("tejnal");

    CompletableFuture.allOf(test1, test2, test3, test4).join();

    return new ResponseEntity<>(OK);
     }
}
