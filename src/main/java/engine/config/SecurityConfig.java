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
            .antMatchers("/actuator/shutdown", "/api/register")
            .permitAll()
            .antMatchers("/h2-console/**")
            .hasRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //TODO remove after adding feature for adding users
        var user = User.withDefaultPasswordEncoder()
                       .username("admin")
                       .password("admin")
                       .roles("ADMIN")
                       .build();
        //TODO add users from database
        //TODO add admin to database
        return new InMemoryUserDetailsManager(user);
    }
}
