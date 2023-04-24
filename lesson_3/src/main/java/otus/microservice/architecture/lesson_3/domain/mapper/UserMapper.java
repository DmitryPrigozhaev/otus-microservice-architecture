package otus.microservice.architecture.lesson_3.domain.mapper;

import otus.microservice.architecture.lesson_3.domain.dto.User;
import otus.microservice.architecture.lesson_3.domain.model.UserDomain;

/**
 * The utility class is responsible for converting the {@link User} to
 * the domain class {@link UserDomain} and vice versa.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
public final class UserMapper {

  /**
   * Utility class can't be instantiated.
   */
  private UserMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Convert DTO to domain entity.
   *
   * @param dto the instance of {@link User}
   * @return the {@link UserDomain}
   */
  public static UserDomain map(User dto) {
    return new UserDomain(
      dto.getId(),
      dto.getUsername(),
      dto.getFirstName(),
      dto.getLastName(),
      dto.getEmail(),
      dto.getPhone()
    );
  }

  /**
   * Convert domain entity to DTO.
   *
   * @param domain the instance of {@link UserDomain}
   * @return the {@link User}
   */
  public static User map(UserDomain domain) {
    return new User(
      domain.getId(),
      domain.getUsername(),
      domain.getFirstName(),
      domain.getLastName(),
      domain.getEmail(),
      domain.getPhone()
    );
  }
}
