package rusoft.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rusoft.project.entity.Client;

@Configuration
public class BasicClientConfig {

    @Bean
    public Client basicClient() {
        return new Client().setBirthYear(0L)
                .setName("Main Storage");
    }
}
