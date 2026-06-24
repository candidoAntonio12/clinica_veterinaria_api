package com.api.clinicaveterinaria.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.clinicaveterinaria.dto.UserDto;
import com.api.clinicaveterinaria.model.UserModel;
import com.api.clinicaveterinaria.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( "api/user" )
public class UserController {

	@Autowired
	private UserService service;
	
	
	public UserController() {}
	public UserController(  UserService service ) {
		this.service = service;
	}
	
	@GetMapping( "{id}" )
	public ResponseEntity< UserModel > getUserByid( @PathVariable UUID id )
	{
		var user = this.service.findById( id ); 
		if ( user == null ) {
			return ( ResponseEntity.notFound().build() );
		}
		return ( ResponseEntity.ok( user ) );
	}
	
	@PostMapping
	public ResponseEntity< ? > cadstrarUser( @Valid @RequestBody UserDto user)
	{
		var value = service.cadastrarUser( user );
		if ( value == null ) {
			return ( ResponseEntity.status( HttpStatus.BAD_REQUEST ).build() );
		}
		return ( ResponseEntity.status( HttpStatus.CREATED ).body( value ) );
	}
	
}
