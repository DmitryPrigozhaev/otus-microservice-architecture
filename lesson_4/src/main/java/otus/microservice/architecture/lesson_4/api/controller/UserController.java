package otus.microservice.architecture.lesson_4.api.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import otus.microservice.architecture.lesson_4.domain.dto.User;
import otus.microservice.architecture.lesson_4.service.UserService;

import java.util.Collection;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * The controller is responsible for user management.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  /**
   * The endpoint is responsible for creating a user.
   *
   * @param user the original incoming payload as {@link User}
   */
  @PostMapping("/user")
  public void create(@RequestBody User user) {
    userService.create(user);
  }

  /**
   * The endpoint is responsible for retrieving user data by ID.
   *
   * @param userId the user identifier
   * @return the {@link User} if found
   */
  @GetMapping("/user/{userId}")
  public User read(@PathVariable Long userId) {
    return userService.get(userId);
  }

  /**
   * The endpoint is responsible for retrieving all users data.
   *
   * @return the {@link User} if found
   */
  @GetMapping("/user")
  public Collection<User> readAll() {
    return userService.getAll();
  }

  /**
   * The endpoint is responsible for update a user.
   *
   * @param request the original incoming payload as {@link UpdateUserRequest}
   */
  @PutMapping("/user/{userId}")
  public void update(@PathVariable Long userId,
                     @RequestBody UpdateUserRequest request) {
    userService.update(UpdateUserRequestConverter.convert(userId, request));
  }

  /**
   * The method is responsible for deleting user by user ID.
   *
   * @param userId the original user ID
   */
  @ResponseStatus(NO_CONTENT) // Disagree with this
  @DeleteMapping("/user/{userId}")
  public void delete(@PathVariable Long userId) {
    userService.delete(userId);
  }

  /**
   * The POJO representing the input request model for updating the user.
   */
  @Getter
  @EqualsAndHashCode
  @ToString
  private static final class UpdateUserRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;

    /**
     * The single class constructor used to correctly deserialize a JSON into an object.
     *
     * @param firstName the user first name
     * @param lastName  the user last name
     * @param email     the user email address
     * @param phone     the user phone
     */
    @JsonCreator
    public UpdateUserRequest(@JsonProperty("firstName") String firstName,
                             @JsonProperty("lastName") String lastName,
                             @JsonProperty("email") String email,
                             @JsonProperty("phone") String phone) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.phone = phone;
    }
  }

  /**
   * The utility class is responsible for converting an
   * incoming request into a specific object.
   */
  static final class UpdateUserRequestConverter {

    /**
     * The utility method is responsible for converting given
     * {@link UpdateUserRequest} into {@link User}.
     *
     * @param id      the original user ID
     * @param request the original incoming request
     * @return the instance of {@link User}
     */
    static User convert(Long id, UpdateUserRequest request) {
      return new User(
        id,
        null,
        request.getFirstName(),
        request.getLastName(),
        request.getEmail(),
        request.getPhone()
      );
    }

    /**
     * Utility class can't be instantiated.
     */
    private UpdateUserRequestConverter() {
      throw new IllegalStateException("Utility class");
    }
  }
}
