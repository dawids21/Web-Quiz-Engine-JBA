package engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/actuator/shutdown", "/h2-console")
            .permitAll()
            .anyRequest()
            .authenticated();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //TODO remove after adding feature for adding users
        var user = User.withDefaultPasswordEncoder()
                       .username("admin")
                       .password("admin")
                       .roles("USER")
                       .build();
        return new InMemoryUserDetailsManager(user);
    }
}