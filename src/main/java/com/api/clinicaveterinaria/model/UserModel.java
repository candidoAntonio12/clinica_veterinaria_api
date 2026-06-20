package com.api.clinicaveterinaria.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private UUID id;
    @Column( nullable = false )
    private String name;
    @Column( nullable =  false )
    private String email;
    @Column( nullable =  false )
    private String password;
    @ElementCollection( fetch = FetchType.LAZY )
    private List<SimpleGrantedAuthority> grantedAuthoritys;

    public UserModel()
    {
        this.grantedAuthoritys = new ArrayList<>();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return ( grantedAuthoritys );
    }
    @Override
    public @Nullable String getPassword() {
        return ( this.password );
    }

    @Override
    public String getUsername() {
        return ( this.email );
    }

    public void addGrantedAuthority( String autority )
    {
        var GrantedAutority = new SimpleGrantedAuthority( autority );
        this.grantedAuthoritys.add(GrantedAutority);
    }

    public UUID getId() {
        return ( this.id );
    }

    public String getName() {
        return ( this.name );
    }

    public String getEmail() {
        return ( this.email );
    }

    public List<SimpleGrantedAuthority> getGrantedAuthoritys() {
        return this.grantedAuthoritys;
    }
    public void setId( UUID id ) {
        this.id = id;
    }
    public void setName( String name ) {
        this.name = name;
    }
    public void setEmail( String email ) {
        this.email = email;
    }
    public void setPassword( String password ) {
        this.password = password;
    }
}
