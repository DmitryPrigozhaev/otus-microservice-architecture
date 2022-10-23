package otus.microservice.architecture.homework_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * OTUS Microservice Architecture: Homework 2.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@PropertySource(value = "classpath:application.properties")
@EnableJpaRepositories(basePackages = "otus.microservice.architecture.homework_2.domain.repository")
@SpringBootApplication
public class Homework2Application {

  public static void main(String[] args) {
    SpringApplication.run(Homework2Application.class, args);
  }
}
