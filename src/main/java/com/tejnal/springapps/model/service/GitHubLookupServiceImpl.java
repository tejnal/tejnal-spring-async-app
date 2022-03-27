package com.tejnal.springapps.model.service;


import com.tejnal.springapps.model.User;
import com.tejnal.springapps.exception.ExternalApiIntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
public class GitHubLookupServiceImpl implements  GitHubLookupService{

    private static final Logger log = LoggerFactory.getLogger(GitHubLookupServiceImpl.class);

    private final  RestTemplate restTemplate;
    private String githubUrl;
    private static final String API_END_PATH = "users/";
    public GitHubLookupServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                   @Value("${github.apiEndPoint}") String githubUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.githubUrl = githubUrl;
    }

    @Override
    public CompletableFuture<User> findByUser(String user)  throws InterruptedException{
        log.info("Looking up: {}", user );
        try {
        URI uri = UriComponentsBuilder.fromHttpUrl(githubUrl)
                .path(API_END_PATH + user)
                .build()
                .encode()
                .toUri();

        User results =  restTemplate.getForObject(uri, User.class);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(results);


        } catch(HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiIntegrationException(e.getMessage(), e);

        }


    }
}
