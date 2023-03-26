package otus.microservice.architecture.lesson_1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The simple REST controller to get application status.
 *
 * @author Dmitry Prigozhaev
 * on 17.07.2022
 */
@Slf4j
@RestController
public class StatusController {

  private static final Map<String, Object> STATUS_RESPONSE = Map.of("status", "OK");

  /**
   * The simple endpoint is responsible for getting application status.
   *
   * @return the application status
   */
  @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> health() {
    log.info("try to get application health...");

    return STATUS_RESPONSE;
  }
}
