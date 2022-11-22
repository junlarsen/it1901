package no.ntnu.cardsnap.rest;

import no.ntnu.cardsnap.core.AbstractCardDeckRepository;
import no.ntnu.cardsnap.core.AbstractCardDeckService;
import no.ntnu.cardsnap.core.AbstractCardRepository;
import no.ntnu.cardsnap.core.AbstractCardService;
import no.ntnu.cardsnap.core.CardDeckRepository;
import no.ntnu.cardsnap.core.CardDeckService;
import no.ntnu.cardsnap.core.CardRepository;
import no.ntnu.cardsnap.core.CardService;
import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Main Spring Boot application.
 */
@SpringBootApplication
public class Application {
  /**
   * Start the Spring Boot application.
   *
   * @param args Java application commandline arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Bean producer for {@link AbstractCardDeckRepository}.
   *
   * @param jdb Database to inject into repository
   * @return The bean
   */
  @Primary
  @Bean
  public AbstractCardDeckRepository createCardDeckRepository(JsonDatabase jdb) {
    return new CardDeckRepository(jdb);
  }

  /**
   * Bean producer for {@link AbstractCardDeckService}.
   *
   * @param repository Repository to inject into service
   * @return The bean
   */
  @Primary
  @Bean
  public AbstractCardDeckService createCardDeckService(AbstractCardDeckRepository repository) {
    return new CardDeckService(repository);
  }

  /**
   * Bean producer for {@link AbstractCardRepository}.
   *
   * @param jdb Database to inject into repository
   * @return The bean
   */
  @Primary
  @Bean
  public AbstractCardRepository createCardRepository(JsonDatabase jdb) {
    return new CardRepository(jdb);
  }

  /**
   * Bean producer for {@link AbstractCardService}.
   *
   * @param repository Repository to inject into service
   * @return The bean
   */
  @Primary
  @Bean
  public AbstractCardService createCardService(AbstractCardRepository repository) {
    return new CardService(repository);
  }
}
