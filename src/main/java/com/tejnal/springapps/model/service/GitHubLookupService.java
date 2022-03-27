package com.tejnal.springapps.model.service;

import com.tejnal.springapps.model.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


public interface GitHubLookupService {

    CompletableFuture<User> findByUser(String user) throws InterruptedException;
}
