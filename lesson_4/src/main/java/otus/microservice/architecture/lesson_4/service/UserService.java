package otus.microservice.architecture.lesson_4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.microservice.architecture.lesson_4.domain.dto.User;
import otus.microservice.architecture.lesson_4.domain.mapper.UserMapper;
import otus.microservice.architecture.lesson_4.domain.model.UserDomain;
import otus.microservice.architecture.lesson_4.domain.repository.UserRepository;
import otus.microservice.architecture.lesson_4.exception.UserNotFoundException;

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
    log.debug("trying to create: {}", user);

    UserDomain domain = UserMapper.map(user);

    UserDomain saved = userRepository.save(domain);

    log.debug("user was successfully created: {}", saved);
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
    log.debug("trying to get user by ID: {}", id);

    return UserMapper.map(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
  }

  @Transactional(readOnly = true)
  public List<User> getAll() {
    log.debug("trying to get all users");

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
    log.debug("trying to update user {} by data: {}", user.getId(), user);

    User existedUser = this.get(user.getId());

    UserDomain savedUser = UserMapper.map(user);
    savedUser.setUsername(existedUser.getUsername()); // speedy crutch

    userRepository.save(savedUser);

    log.debug("user was successfully updated: {}", savedUser);
  }

  /**
   * The method is responsible for deleting {@link User} by given ID.
   *
   * @param id the {@link User} identifier
   * @throws UserNotFoundException if user with given ID not found
   */
  @Transactional
  public void delete(Long id) {
    log.debug("trying to delete user: {}", id);

    userRepository.delete(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));

    log.debug("user with ID '{}' was successfully deleted", id);
  }
}
