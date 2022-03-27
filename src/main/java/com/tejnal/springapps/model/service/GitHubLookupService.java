package com.tejnal.springapps.model.service;

import com.tejnal.springapps.model.UserResponse;

import java.util.concurrent.CompletableFuture;


public interface GitHubLookupService {

    CompletableFuture<UserResponse> findByUser(String user) throws InterruptedException;
}
