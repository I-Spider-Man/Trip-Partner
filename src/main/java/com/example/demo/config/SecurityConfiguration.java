package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        return daoAuthenticationProvider;
	    }
	 

	
    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
    	http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .authorizeHttpRequests(Authorize -> Authorize
        		.requestMatchers("/Admin","/Admin/signin").permitAll()
        		.requestMatchers("/Admin/**").authenticated()
                .anyRequest().permitAll())
        .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable());       
        return http.build();
    }

	
	
	}


   

	

    

