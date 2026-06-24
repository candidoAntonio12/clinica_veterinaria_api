package com.api.clinicaveterinaria.security;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


public class Cryptography {
	
	private static PasswordEncoder encoderOf() 
	{	
		return (  PasswordEncoderFactories
				.createDelegatingPasswordEncoder() );
	}
	
	public static String  encoder( String raw ) 
	{
		if ( raw == null )
			return ( null );
		return ( Cryptography.encoderOf().encode(raw) );
	}
	public static boolean compare ( String raw, String encodePassword ) {
		
		if ( raw == null ||  encodePassword == null  )
			return ( false );
		return ( Cryptography.encoderOf().matches(raw, encodePassword) );
	}
}
