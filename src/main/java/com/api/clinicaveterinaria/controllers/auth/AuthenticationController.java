package com.api.clinicaveterinaria.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.clinicaveterinaria.dto.LoginDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@GetMapping( "/login" )
	public ResponseEntity<?> login( @Valid @RequestBody LoginDto login )
	{
		return ( null );
	}
	
	@GetMapping( "/refresh" )
	public ResponseEntity<?> refreshToken( @RequestBody String token )
	{
		return ( null );
	} 
	
}
