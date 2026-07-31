package com.tuto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Personne personne() {
        Personne p = new Personne();
        p.setFirstname("John");
        p.setLastname("Doe");
        p.setAge(30);
        return p;
    }
}
