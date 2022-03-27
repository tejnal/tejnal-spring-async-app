package com.tejnal.springapps;

import com.tejnal.springapps.model.service.GitHubLookupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class TejnalSpringAsyncAppApplication {
  private static final Logger log = LoggerFactory.getLogger(TejnalSpringAsyncAppApplication.class);

  @Autowired GitHubLookupServiceImpl gitHubLookupService;

  public static void main(String[] args) {
    SpringApplication.run(TejnalSpringAsyncAppApplication.class, args);
  }

  @Bean("threadPoolTaskExecutor")
  public TaskExecutor getAsyncTaskExecutor() {

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(20);
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setThreadNamePrefix("Async-");
    return executor;
  }

/*  @Override
  public void run(String... args) throws Exception {
    long start = System.currentTimeMillis();


    // Kick of multiple, asynchronous lookups
    CompletableFuture<UserResponse> test1 = gitHubLookupService.findByUser("PivotalSoftware");
    CompletableFuture<UserResponse> test2 = gitHubLookupService.findByUser("CloudFoundry");
    CompletableFuture<UserResponse> test3 = gitHubLookupService.findByUser("Spring-Projects");
    CompletableFuture<UserResponse> test4 = gitHubLookupService.findByUser("tejnal");

    CompletableFuture.allOf(test1, test2, test3, test4).join();

    log.info("Elapsed time: {}", (System.currentTimeMillis() - start));

      log.info("Task1 --> " + test1.get());
	  log.info("Task2 --> " + test2.get());
	  log.info("Task3 --> " + test3.get());
	  log.info("Task4 --> " + test4.get());
  }*/
}
