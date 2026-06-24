package com.api.clinicaveterinaria.security;

import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.annotation.Nullable;

public class JwtUtis {
    
	private static  String JWTSECRECT;
	private static long EXPIRATION;
	
	private static void propertiesParse() 
	{

		if ( JwtUtis.JWTSECRECT != null )
			return ;
		var r = ResourceBundle.getBundle("application");
		if ( r.containsKey( "jwt.secret" ) && r.containsKey( "jwt.expiration" )) 
		{
			JwtUtis.JWTSECRECT =  r.getString("jwt.secret");
			JwtUtis.EXPIRATION = Long.parseLong( r.getString("jwt.expiration") );
		}
		
	}
	
    public static @Nullable String encoder( UserDetails user )
    {
    	JwtUtis.propertiesParse();
        String jwthash= JWT.create()
        					.withSubject( user.getUsername() )
        					.withIssuer( "cananton" )
        					.withIssuedAt( new Date() )
        					.withExpiresAt( new Date(System.currentTimeMillis() + (EXPIRATION * 1000 * 1000)) )
        					.sign( Algorithm.HMAC512(JwtUtis.JWTSECRECT) );
      
        return ( jwthash );
    }
    public static @Nullable String getSubject( String hash ) 
    {
    	JwtUtis.propertiesParse();
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
