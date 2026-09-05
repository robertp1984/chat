package org.softwarecave.chat.config.security;

import org.springframework.boot.health.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.security.autoconfigure.actuate.web.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(getAuthorizationManagerRequestMatcherRegistryCustomizer())
                .oauth2ResourceServer(config -> config.jwt(Customizer.withDefaults()));

        return http.build();
    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> getAuthorizationManagerRequestMatcherRegistryCustomizer() {
        return auth -> auth
                // Permit access for Kubernetes
                .requestMatchers(EndpointRequest.to(HealthEndpoint.class)).permitAll()

                // Permit access for Prometheus
                .requestMatchers(EndpointRequest.to("prometheus").withHttpMethod(HttpMethod.GET)).anonymous()

                // Actuator
                .requestMatchers(EndpointRequest.toAnyEndpoint().withHttpMethod(HttpMethod.GET)).hasAnyAuthority(Role.ACTUATOR_READ.getTitle())
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasAnyAuthority(Role.ACTUATOR_WRITE.getTitle())

                // Summary
                .requestMatchers("/api/v1/summarization").hasAnyAuthority(Role.SUMMARY_ALL.getTitle())

                // Weather
                .requestMatchers("/api/v1/weatherSuggestion").hasAnyAuthority(Role.WEATHER_ALL.getTitle())

                // Other
                .anyRequest().denyAll();
    }
}
