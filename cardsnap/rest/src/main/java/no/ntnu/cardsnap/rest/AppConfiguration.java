package no.ntnu.cardsnap.rest;

import no.ntnu.cardsnap.persistence.DiskJsonModelStorage;
import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Bean provider configuration.
 *
 * <p>Class that is capable of providing beans for Spring Boot to inject into
 * components.
 */
@Configuration(proxyBeanMethods = false)
public class AppConfiguration {
  /**
   * Provide a bean producer for {@link JsonDatabase}
   *
   * <p>This returns a JsonDatabase backed by a DiskJsonModelStorage that will
   * store data in the current working directory.
   *
   * @return The bean compatible JsonDatabase
   */
  @Bean
  public JsonDatabase createJsonDatabase() {
    return new JsonDatabase(new DiskJsonModelStorage(System.getProperty("user.dir")));
  }
}
