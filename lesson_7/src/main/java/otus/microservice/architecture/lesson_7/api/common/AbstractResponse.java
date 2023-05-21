package otus.microservice.architecture.lesson_7.api.common;

import lombok.Getter;

/**
 * <pre>
 * ┌──────────────────────────────────────────────────────────────────────────────────────┐
 * │                 The general JSON format of the application response.                 │
 * └──────────────────────────────────────────────────────────────────────────────────────┘
 * </pre>
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Getter
@SuppressWarnings("java:S1610")// Abstract classes without fields should be converted to interfaces
public abstract class AbstractResponse {

  /**
   * Basic constructor to create an application response object.
   */
  protected AbstractResponse() {
  }
}
