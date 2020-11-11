package engine.config;

import engine.utils.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public ObjectMapper getObjectMapperUtils() {
        return new ObjectMapper();
    }
}
