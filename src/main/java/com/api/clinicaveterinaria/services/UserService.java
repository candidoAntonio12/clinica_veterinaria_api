package com.api.clinicaveterinaria.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.clinicaveterinaria.dto.UserDto;
import com.api.clinicaveterinaria.model.UserModel;
import com.api.clinicaveterinaria.repository.UserRepository;
import com.api.clinicaveterinaria.security.Cryptography;

import jakarta.annotation.Nullable;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
    private  UserRepository repository;

	public UserService(){}
	
	public UserService(  UserRepository repository ) 
	{
		this.repository = repository;
	}
	
	public UserModel save( UserModel model ) 
	{
		
		if ( model == null )
			return ( model );
		return ( this.repository.save( model ) );
	}

	@Override
	@SuppressWarnings( "null" )
    public @Nullable  UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException 
    {
		if ( username == null )
			return ( null );
        Optional <UserModel> user = repository.findByEmail( username );
        return ( user.orElseThrow() );
    }
	
	public @Nullable UserModel findById( UUID id )
	{
		if ( id == null )
			return ( null );
		return ( this.repository.findById(id).orElseThrow() );
	}
	
	public @Nullable UserModel cadastrarUser( UserDto userDto ) {
		
		if ( userDto == null )
			return ( null );
		UserModel model = new UserModel();
		model.setName( userDto.name() );
		model.setEmail( userDto.email() );
		model.setPassword( Cryptography.encoder(userDto.password()) );
		return ( this.save(model) );
	}
}
