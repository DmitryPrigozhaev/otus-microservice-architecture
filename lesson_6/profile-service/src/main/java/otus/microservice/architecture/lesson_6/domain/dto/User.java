package otus.microservice.architecture.lesson_6.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import otus.microservice.architecture.lesson_6.domain.model.UserDomain;

/**
 * POJO represents the {@link UserDomain} data transfer object.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Getter
@EqualsAndHashCode
@ToString
public class User {

  private final Long id;
  private final String username;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String phone;

  /**
   * The default class constructor.
   *
   * @param id        the user identifier
   * @param username  the username
   * @param firstName the user first name
   * @param lastName  the user last name
   * @param email     the user email address
   * @param phone     the user phone
   */
  public User(Long id,
              String username,
              String firstName,
              String lastName,
              String email,
              String phone) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }
}
