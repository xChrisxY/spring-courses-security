package com.chris.authentication.auth.security;

import com.chris.authentication.auth.security.filters.JwtAuthenticationFilter;
import com.chris.authentication.auth.security.filters.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager);
        JwtValidationFilter jwtValidationFilter = new JwtValidationFilter(authenticationManager);

        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");

        return httpSecurity.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users/me").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}/role").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/courses").hasRole("TEACHER")
                .requestMatchers(HttpMethod.GET, "/api/v1/courses").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/courses/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/courses/{id}").hasRole("TEACHER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/courses/{id}").hasAnyRole("TEACHER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/courses/{id}/lessons").hasRole("TEACHER")
                .requestMatchers(HttpMethod.GET, "/api/v1/courses/{id}/lessons").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/lessons/{id}").hasRole("TEACHER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/lessons/{id}").hasRole("TEACHER")
                .requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/categories").permitAll()

                .anyRequest().authenticated())
                .addFilter(jwtAuthenticationFilter)
                .addFilter(jwtValidationFilter)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
