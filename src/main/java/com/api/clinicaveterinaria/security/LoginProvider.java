package com.api.clinicaveterinaria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.clinicaveterinaria.dto.LoginDto;
import com.api.clinicaveterinaria.services.UserService;

@Component
public class LoginProvider {
	
	@Autowired
	private UserService userService;
	
	public LoginProvider () {}
	
	public LoginProvider ( UserService userService ) {
		this.userService = userService;
	}
	
	public String getTokenJwt( LoginDto login ) {
		if ( login == null )
			return ( null );
		var user = userService.loadUserByUsername( login.email() );
		if ( user == null )
			return ( null );
		if ( Cryptography.compare(login.password(), user.getPassword()) )
			return ( JwtUtis.encoder(user) );
		return ( null );
	}
}
