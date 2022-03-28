package com.tejnal.springapps.model.service;

import com.tejnal.springapps.model.PersonResponse;

import java.util.concurrent.CompletableFuture;


public interface GitHubLookupService {

    CompletableFuture<PersonResponse> findByUser(String user) throws InterruptedException;
}
