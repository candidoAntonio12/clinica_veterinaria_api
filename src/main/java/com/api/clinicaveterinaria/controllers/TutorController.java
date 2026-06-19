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

import com.api.clinicaveterinaria.dto.TutorDto;
import com.api.clinicaveterinaria.model.TutorModel;
import com.api.clinicaveterinaria.services.TutorService;

import jakarta.validation.Valid;

@RestController(  "TutorController" )
@RequestMapping( "/api/tutor" )
public class TutorController {

    @Autowired
    private TutorService service;

    @GetMapping("/{id}")
    public ResponseEntity<TutorModel> getbyIdTutor( @PathVariable(  name = "id" ) UUID id ) {
        
        return ( ResponseEntity.ofNullable( service.findById( id ) ) );
    }

    @PostMapping
    public ResponseEntity< Object >  postCadastrarTutor( @Valid @RequestBody  TutorDto tutorDto )
    {
        TutorModel tutor = new TutorModel();
        tutor.setName( tutorDto.name() );
        tutor.setEmail( tutorDto.email() );
        tutor.setTelephone( tutorDto.telephone() );
        tutor.setAddress( tutorDto.address() ); 
        tutor = service.save(tutor); 
        if ( tutor == null )
        {
            return ( ResponseEntity.status( HttpStatus.BAD_REQUEST )
                                    .body(null) 
                   );
        }
        return  ( ResponseEntity.status( HttpStatus.CREATED )
                                .body( service.toHateoasBuilder( tutor ) ) 
                );
    }
}
