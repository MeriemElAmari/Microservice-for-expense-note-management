package com.novelis.novy.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.novelis.novy.enums.Permission.*;
import static com.novelis.novy.enums.Role.ADMIN;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalAuthentication
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    //at the start of the application spring boot will look for a bean of type filter chain
    // it is the bean responsible for all the security config in our app

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf((csrf)->csrf.disable())
                //white list : end point that does not require any token
                // permit for urls in requestMatchers everything but any other request should be authenticated
                .authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests
                        .requestMatchers("/api/v1/auth/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") // Secure endpoints starting with "/admin" for users with the "ADMIN" role
                        .requestMatchers("/api/v1/user/**").hasRole("USER") // Secure endpoints starting with "/admin" for users with the "ADMIN" role
                        .anyRequest().authenticated())
                // configure session manager :  every request should be authenticated so the session state should not be stored
                .sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                // adding the jwt filter that we created before the UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);// adding the jwt filter
        return httpSecurity.build();
    }
}
