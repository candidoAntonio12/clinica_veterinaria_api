package com.api.clinicaveterinaria.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.clinicaveterinaria.dto.LoginDto;
import com.api.clinicaveterinaria.security.LoginProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
	
	@Autowired
	private LoginProvider provider;
	
	public AuthenticationController() {}
	
	public AuthenticationController( LoginProvider provider ) 
	{
		this.provider = provider;
	}
	
	@PostMapping( "login" )
	public ResponseEntity< String > login( @Valid @RequestBody LoginDto login )
	{
		var token = provider.getTokenJwt( login );
		if ( token == null ) {
			return ( ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						  .build() );
		}
		return ( ResponseEntity.ok( token ) );
	}
	
	@PostMapping( "refresh" )
	public ResponseEntity<?> refreshToken( @RequestBody String token )
	{
		return ( null );
	} 
	
}
