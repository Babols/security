package com.wildcodeschool.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/avengers/assemble").hasRole("CHAMPION")
                .mvcMatchers("/secret-bases").hasRole("DIRECTOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        return http.build();
    }
    @Bean
    public UserDetailsService users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails Steve = users
                .username("Steve")
                .password("motdepasse")
                .roles("CHAMPION")
                .build();
        UserDetails Nick = users
                .username("Nick")
                .password("flerken")
                .roles("DIRECTOR")
                .build();
        return new InMemoryUserDetailsManager(Steve, Nick);
    }

}