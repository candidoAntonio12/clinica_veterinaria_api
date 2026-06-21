package com.api.clinicaveterinaria.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.api.clinicaveterinaria.controllers.TutorController;
import com.api.clinicaveterinaria.model.TutorModel;
import com.api.clinicaveterinaria.repository.TutorRepository;
import com.utils.exception.NullObjectException;

import jakarta.transaction.Transactional;

@Service
@SuppressWarnings( "null" )
public class TutorService {

    @Autowired
    private  TutorRepository repository;
    
    public TutorService() {}
    
    public TutorService( TutorRepository repository ) 
    {
    	NullObjectException.isNullObject(repository);
    	this.repository = repository;
    }
    
    @Transactional
    public TutorModel save( TutorModel tutor )
    {
    	NullObjectException.isNullObject(tutor);
        return ( this.repository.save( tutor ) );
    }

    public TutorModel findById( UUID id )
    {
    	NullObjectException.isNullObject(id);
        return ( this.repository.findById( id ).get() );
    }
    
    public EntityModel< TutorModel > toHateoasBuilder( TutorModel tutor  )
    {
    	NullObjectException.isNullObject(tutor);
        EntityModel< TutorModel > entityModel =  EntityModel.of( tutor) ;
         entityModel.add( 
                            linkTo( TutorController.class  )
                            .slash( tutor.getId() )
                            .withRel("cadastrar")
                        );
         return ( entityModel );
    }
}
