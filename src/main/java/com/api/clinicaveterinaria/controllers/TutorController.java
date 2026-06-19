package com.api.clinicaveterinaria.controllers;

import com.api.clinicaveterinaria.dto.TutorDto;
import com.api.clinicaveterinaria.model.TutorModel;
import com.api.clinicaveterinaria.services.TutorService;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController(  "TutorController" )
@RequestMapping( "/api" )
public class TutorController {

    @Autowired
    private TutorService service;

    @GetMapping( "/tutor" )
    public ResponseEntity<TutorModel> getTutors() {
        

        return ( null );
    }

    @PostMapping( "/tutor" )
    public ResponseEntity< Object >  postCadastrarTutor( @Valid @RequestBody  TutorDto tutorDto )
    {
        TutorModel tutor = new TutorModel();
        tutor.setName( tutorDto.name() );
        tutor.setEmail( tutorDto.email() );
        tutor.setTelephone( tutorDto.telephone() );
        tutor.setAddress( tutorDto.address() );      
        return ( ResponseEntity.ok( service.save(tutor)  ) );
    }
}
