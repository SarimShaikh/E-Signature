package webApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import webApp.entities.audit.AuditorAwareImpl;

/**
 * Created by Sarim on 5/1/2020.
 */
@SpringBootApplication(scanBasePackages = "webApp")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class WebApp {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}
