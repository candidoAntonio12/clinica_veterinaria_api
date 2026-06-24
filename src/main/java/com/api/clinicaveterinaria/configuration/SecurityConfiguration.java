package com.api.clinicaveterinaria.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.api.clinicaveterinaria.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	JwtAuthenticationFilter filter;
	
	public SecurityConfiguration() {}
	
	public SecurityConfiguration ( JwtAuthenticationFilter filter ) {
		this.filter = filter;
	}
	
    @Bean
    SecurityFilterChain filterChain( HttpSecurity http ) throws Exception
    {
       var security = http.cors( Customizer.withDefaults() )
            .csrf( (c) -> c.disable() )
            .addFilterBefore( this.filter , BasicAuthenticationFilter.class )
            .authorizeHttpRequests( (c) -> c
            		.requestMatchers("/api/auth/login")
            		.permitAll()
            		.anyRequest()
            		.authenticated() )
            .sessionManagement( (e) -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
            .build();
        return ( security );
    }
    
}
