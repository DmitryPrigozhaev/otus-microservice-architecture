package otus.microservice.architecture.lesson_7.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.microservice.architecture.lesson_7.domain.dto.User;
import otus.microservice.architecture.lesson_7.domain.mapper.UserMapper;
import otus.microservice.architecture.lesson_7.domain.model.UserDomain;
import otus.microservice.architecture.lesson_7.domain.repository.UserRepository;
import otus.microservice.architecture.lesson_7.exception.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The simplest service is responsible for user management.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  /**
   * The method is responsible for creating a new {@link User}.
   *
   * @param user the {@link User} to create
   */
  @Transactional
  public void create(User user) {
    log.debug("Trying to create: {}", user);

    UserDomain domain = UserMapper.map(user);

    UserDomain saved = userRepository.save(domain);

    log.debug("User was successfully created: {}", saved);
  }

  /**
   * The method is responsible for retrieving {@link User} by ID.
   *
   * @param id the {@link User} identifier
   * @return the {@link User} if found
   * @throws UserNotFoundException if user with given ID not found
   */
  @Transactional(readOnly = true)
  public User get(Long id) {
    log.debug("Trying to get user by ID: {}", id);

    return UserMapper.map(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
  }

  @Transactional(readOnly = true)
  public List<User> getAll() {
    log.debug("Trying to get all users");

    return userRepository.findAll().stream().map(UserMapper::map).collect(Collectors.toList());
  }

  /**
   * The method is responsible for updating {@link User}.
   *
   * @param user the {@link User} to update
   * @throws UserNotFoundException if user with given ID not found
   */
  @Transactional
  public void update(User user) {
    log.debug("Trying to get user by data: {}", user);

    User existedUser = this.get(user.getId());

    UserDomain savedUser = UserMapper.map(user);
    savedUser.setUsername(existedUser.getUsername()); // speedy crutch

    userRepository.save(savedUser);

    log.debug("User was successfully created: {}", savedUser);
  }

  /**
   * The method is responsible for deleting {@link User} by given ID.
   *
   * @param id the {@link User} identifier
   * @throws UserNotFoundException if user with given ID not found
   */
  @Transactional
  public void delete(Long id) {
    log.debug("Trying to delete user with ID: {}", id);

    userRepository.delete(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));

    log.debug("User with ID '{}' was successfully deleted", id);
  }
}
