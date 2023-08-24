package com.rampagemc.authorization.configuraiton.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("authorization.jwt")
@Configuration
@Getter
@Setter
public class JwtProperties {

    @NotBlank
    private String secret;

    @Positive
    private Long expirationSeconds;
}
