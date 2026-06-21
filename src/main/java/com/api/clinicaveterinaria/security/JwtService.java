package com.api.clinicaveterinaria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.clinicaveterinaria.model.UserModel;
import com.api.clinicaveterinaria.services.UserService;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.utils.exception.NullObjectException;

@Component
public class JwtService {

	@Autowired
	private UserService service;
	private String header;
	private static final String PREFIX_TOKEN = "Bearer ";
	
	public JwtService( UserService service, String header ) 
	{
		NullObjectException.isNullObject( service, header );
		this.service = service;
		this.header = header;
	}

	public JwtService( String header ) 
	{
		NullObjectException.isNullObject( header );
		this.header = header;
	}
	private String  gethashToken() throws JWTDecodeException
	{
		String hash;
		
		if ( !this.header.startsWith( PREFIX_TOKEN ) )
			throw new JWTDecodeException( "No search Prefix" );
		hash = this.header.replace( JwtService.PREFIX_TOKEN, "" );
		return ( hash );
	}
	
	public UserModel getUser() {
		
		UserModel user = null;
		try 
		{
			String hash = gethashToken();
			String username = JwtUtis.jwtIsvalid( hash );
			user = ( UserModel )service.loadUserByUsername( username );
		}
		catch( Exception e ) 
		{
			e.printStackTrace();
		}
		return ( user );
	}
}
