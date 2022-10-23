package otus.microservice.architecture.homework_2.config;

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
@Configuration(proxyBeanMethods = false)
public class MariaDBConfig {

  @Bean
  public DataSource mysqlDataSource(Environment environment) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("datasource.driver")));
    dataSource.setUrl(environment.getProperty("datasource.url"));
    dataSource.setUsername(environment.getProperty("datasource.username"));
    dataSource.setPassword(environment.getProperty("datasource.password"));

    return dataSource;
  }
}
