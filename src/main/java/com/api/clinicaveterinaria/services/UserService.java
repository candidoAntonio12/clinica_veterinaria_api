package com.api.clinicaveterinaria.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.clinicaveterinaria.model.UserModel;
import com.api.clinicaveterinaria.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException 
    {
        Optional <UserModel> user = repository.findByEmail( username );
        return ( user.orElseThrow() );
    }
}
