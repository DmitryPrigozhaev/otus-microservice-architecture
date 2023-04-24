package otus.microservice.architecture.lesson_3.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otus.microservice.architecture.lesson_3.domain.model.UserDomain;

/**
 * The {@link UserDomain} repository class.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {

}
