package com.rampagemc.authorization.configuraiton;

import com.rampagemc.authorization.application.service.ApplicationUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().disable()
                .rememberMe().disable()
                .authorizeHttpRequests(matcher -> matcher
                        .requestMatchers("/web/register").permitAll()
                        .anyRequest().authenticated())
                .httpBasic()
                .and().build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new ApplicationUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Стойкость и версия настроены по образу и подобию LimboAuth.
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10);
    }
}
