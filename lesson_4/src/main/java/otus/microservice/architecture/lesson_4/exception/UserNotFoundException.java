package otus.microservice.architecture.lesson_4.exception;

import lombok.Getter;

/**
 * An exception occurs when the requested user is not found.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Getter
public class UserNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "User with ID '%s' not found";

  private final Long id;

  /**
   * Constructs an {@code UserNotFoundException} with the default specified message.
   *
   * @param id the unknown user ID
   */
  public UserNotFoundException(Long id) {
    super(String.format(DEFAULT_MESSAGE, id));

    this.id = id;
  }
}
