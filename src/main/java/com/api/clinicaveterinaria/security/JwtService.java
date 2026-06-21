package com.api.clinicaveterinaria.security;


import com.api.clinicaveterinaria.model.UserModel;
import com.api.clinicaveterinaria.services.UserService;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.utils.exception.NullObjectException;

import jakarta.servlet.http.HttpServletRequest;

public class JwtService {

	private UserService service;
	private String header;
	private static final String PREFIX_TOKEN = "Bearer ";
	
	public JwtService( UserService service, String header ) 
	{
		NullObjectException.isNullObject( service, header );
		this.service = service;
		this.header = header;
	}
	
	public JwtService( UserService service, HttpServletRequest request ) 
	{
		NullObjectException.isNullObject( service, request );
		this.service = service;
		this.header = request.getHeader( "Authorization" );;
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
			String username = JwtUtis.getSubject( hash );
			user = ( UserModel )service.loadUserByUsername( username );
		}
		catch( Exception e ) 
		{
			e.printStackTrace();
		}
		return ( user );
	}
	public void setHeader( String header ) 
	{
		NullObjectException.isNullObject( header );
		this.header = header;
	}
}
