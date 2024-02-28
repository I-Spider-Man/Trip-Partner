package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
        .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable());       
        return http.build();
    }

	
	
	}


   

	

    

