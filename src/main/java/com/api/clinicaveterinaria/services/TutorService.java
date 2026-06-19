package com.api.clinicaveterinaria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
