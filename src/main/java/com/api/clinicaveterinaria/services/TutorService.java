package com.api.clinicaveterinaria.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.api.clinicaveterinaria.controllers.TutorController;
import com.api.clinicaveterinaria.model.TutorModel;
import com.api.clinicaveterinaria.repository.TutorRepository;

import jakarta.transaction.Transactional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Transactional
    public TutorModel save( TutorModel tutor )
    {
        return ( this.repository.save( tutor ) );
    }

    public TutorModel findById( UUID id )
    {
        return ( this.repository.findById( id ).get() );
    }
    
    public EntityModel< TutorModel > toHateoasBuilder( TutorModel tutor  )
    {
         EntityModel< TutorModel > entityModel =  EntityModel.of( tutor) ;
         entityModel.add( 
                            linkTo( TutorController.class  )
                            .slash( tutor.getId() )
                            .withRel("cadastrar")
                        );
         return ( entityModel );
    }
}
