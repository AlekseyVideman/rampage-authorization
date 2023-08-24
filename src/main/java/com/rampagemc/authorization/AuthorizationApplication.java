package com.rampagemc.authorization;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class AuthorizationApplication {

    public static void main(String[] args) {
        var app = new SpringApplication(AuthorizationApplication.class);
            app.setBannerMode(Banner.Mode.OFF);
            app.run(args);
    }

}
