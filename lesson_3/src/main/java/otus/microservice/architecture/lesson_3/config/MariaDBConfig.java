package otus.microservice.architecture.lesson_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class MariaDBConfig {

  @Bean
  public DataSource mysqlDataSource(Environment environment) {
    final String datasourceDriver = Objects.requireNonNull(environment.getProperty("datasource.driver"));
    final String datasourceUrl = environment.getProperty("datasource.url");
    final String datasourceUsername = environment.getProperty("datasource.username");
    final String datasourcePassword = environment.getProperty("datasource.password");

    log.info("datasourceDriver: {}", datasourceDriver);
    log.info("datasourceUrl: {}", datasourceUrl);
    log.info("datasourceUsername: {}", datasourceUsername);
    log.info("datasourcePassword: {}", datasourcePassword);

    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(datasourceDriver);
    dataSource.setUrl(datasourceUrl);
    dataSource.setUsername(datasourceUsername);
    dataSource.setPassword(datasourcePassword);

    return dataSource;
  }
}
