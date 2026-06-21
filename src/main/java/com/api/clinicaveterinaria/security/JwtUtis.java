package com.api.clinicaveterinaria.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.annotation.Nullable;

public class JwtUtis {
    
	@Value("$(jwt.secret)")
	private static  String JWTSECRECT;
	@Value("$(jwt.expiration)")
	private static long EXPIRATION;
	
	
    public static @Nullable String encoder( UserDetails user )
    {
        String jwthash= JWT.create()
        					.withSubject( user.getUsername() )
        					.withIssuer( "cananton" )
        					.withIssuedAt( new Date() )
        					.withExpiresAt( new Date(System.currentTimeMillis() + EXPIRATION) )
        					.sign( Algorithm.HMAC512(JwtUtis.JWTSECRECT) );
      
        return ( jwthash );
    }
    public static @Nullable String jwtIsvalid( String hash ) 
    {
    	try 
    	{ 
	    	JWTVerifier verify = JWT.require( Algorithm.HMAC512(JwtUtis.JWTSECRECT) )
	    		.withIssuer("cananton" )
	    		.build();
	    	return ( verify.verify( hash ).getSubject() );
    	}
    	catch ( JWTVerificationException e ) 
    	{
    		e.printStackTrace();
    	}
    	return ( null );
    }
    
}
