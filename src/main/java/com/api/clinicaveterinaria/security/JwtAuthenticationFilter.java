package com.api.clinicaveterinaria.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.clinicaveterinaria.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService; 
	
	public JwtAuthenticationFilter( UserService userService ) {
		this.userService = userService;
	}
	
	@Override
	@SuppressWarnings( { "null", "static-access" } )
    protected void doFilterInternal( HttpServletRequest request,
    								 HttpServletResponse response,
    								 FilterChain filterChain )
            throws ServletException, IOException 
	{
		
		JwtService service = new JwtService( userService , request );
    	var user = service.getUser();
    	if ( user == null ) 
    	{
    		response.sendError( response.SC_UNAUTHORIZED );
    		return ;
    	}
    	var auth = new UsernamePasswordAuthenticationToken( user, null, user.getAuthorities() );
    	SecurityContextHolder.getContext().setAuthentication( auth );
    	filterChain.doFilter( request, response );
    }
}
