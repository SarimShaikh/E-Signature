package webBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Sarim on 5/1/2020.
 */
@SpringBootApplication(scanBasePackages = "webBase")
public class WebBaseApp {
    public static void main(String[] args) {
        SpringApplication.run(WebBaseApp.class, args);
    }
}
