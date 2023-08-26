package com.rampagemc.authorization.configuraiton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationEntryPoint authenticationEntryPoint) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().disable()
                .rememberMe().disable()
                .authorizeHttpRequests(matcher -> matcher
                        .requestMatchers("/web/register", "/actuator/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().build();
    }

    /** BCrypt configuration as in the LimboAuth plugin.
     * <p>The Strength parameter is equals to the cost value in the LimboAuth
     * <a href="https://github.com/Elytrium/LimboAuth/blob/master/src/main/java/net/elytrium/limboauth/model/RegisteredPlayer.java#L114">RegisteredPlayer::genHash</a> method.</p>
     * The {@link BCryptPasswordEncoder.BCryptVersion} is equals to the value specified in
     * the <a href="https://github.com/patrickfav/bcrypt/blob/main/modules/bcrypt/src/main/java/at/favre/lib/crypto/bcrypt/BCrypt.java#L63">Favre Bcrypt library</a> which is used in LimboAuth.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10);
    }
}
