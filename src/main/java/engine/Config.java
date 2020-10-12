package engine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public QuizRepository quizRepository() {
        return new QuizRepository();
    }

    @Bean
    public QuizChecker quizChecker() {
        return new QuizChecker(quizRepository());
    }
}
