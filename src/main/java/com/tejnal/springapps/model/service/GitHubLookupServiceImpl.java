package com.tejnal.springapps.model.service;

import com.tejnal.springapps.exception.ExternalApiIntegrationException;
import com.tejnal.springapps.mapper.IUserResponseToUserEntityMapper;
import com.tejnal.springapps.model.PersonResponse;
import com.tejnal.springapps.repository.PersonRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
@Scope( proxyMode = ScopedProxyMode.TARGET_CLASS )
public class GitHubLookupServiceImpl implements GitHubLookupService {

  private static final Logger log = LoggerFactory.getLogger(GitHubLookupServiceImpl.class);
  private static final String API_END_PATH = "users/";
  private final RestTemplate restTemplate;
  private IUserResponseToUserEntityMapper userResponseToOrderEntityMapper =
      Mappers.getMapper(IUserResponseToUserEntityMapper.class);
  private String githubUrl;
  private PersonRepository personRepository;

  public GitHubLookupServiceImpl(
      RestTemplateBuilder restTemplateBuilder,
      @Value("${github.apiEndPoint}") String githubUrl,
      PersonRepository personRepository) {
    this.restTemplate = restTemplateBuilder.build();
    this.githubUrl = githubUrl;
    this.personRepository = personRepository;
  }

  @Override
  @Async("asyncExecutor")
  public CompletableFuture<PersonResponse> findByUser(String user) throws InterruptedException {
    log.info("Looking up: {}", user);
    try {
      URI uri =
          UriComponentsBuilder.fromHttpUrl(githubUrl)
              .path(API_END_PATH + user)
              .build()
              .encode()
              .toUri();

      PersonResponse results = restTemplate.getForObject(uri, PersonResponse.class);
      var saveUser = userResponseToOrderEntityMapper.mapOrderResponseToOrderEntity(results);
      var existingPerson= personRepository.getByPersonIdAndName(saveUser.getPersonId(),
              saveUser.getName());
      if (existingPerson.isPresent()) {
        log.info("user record is already exist");
      } else {
        personRepository.save(saveUser);
      }

      //Thread.sleep(1000);
      return CompletableFuture.completedFuture(results);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      throw new ExternalApiIntegrationException("NO Results found for this", e);
    }
  }
}
