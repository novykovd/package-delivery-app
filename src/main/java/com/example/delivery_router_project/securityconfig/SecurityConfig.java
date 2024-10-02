package com.example.delivery_router_project.securityconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService detailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests) -> {requests
                        .requestMatchers("/register", "/login", "/logout").permitAll()
                        .anyRequest().authenticated();})
                .formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/user/home").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll());

        return http.build();
    }
}
