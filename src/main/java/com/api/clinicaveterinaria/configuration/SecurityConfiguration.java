package com.api.clinicaveterinaria.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    private SecurityFilterChain filterChain( HttpSecurity http ) throws Exception
    {
        http.cors( Customizer.withDefaults() )
            .csrf( (c) -> c.disable() )
            .authorizeHttpRequests( (c) -> c.anyRequest().permitAll() );
        return ( http.build() );
    }
    
    @Bean
    private AuthenticationManager authenticationManager( AuthenticationConfiguration configuratiion ) 
    {
    	return ( configuratiion.getAuthenticationManager() );
    }
}
