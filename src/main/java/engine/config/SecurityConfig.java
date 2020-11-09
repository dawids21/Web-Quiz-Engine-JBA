package engine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
            .sameOrigin();
    }
}
