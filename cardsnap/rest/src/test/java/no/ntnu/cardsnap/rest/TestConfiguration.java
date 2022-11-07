package no.ntnu.cardsnap.rest;

import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class TestConfiguration extends PersistenceDependentTestCase {
    @Bean
    public JsonDatabase getJsonDatabase() {
        return jdb;
    }
}
