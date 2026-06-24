package com.api.clinicaveterinaria.model;

import java.io.Serializable;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AnimalModel implements Serializable{

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID id;
    @Column( nullable = false )
    private String name;
    @Column( nullable = false )
    private String species;
    @ManyToOne
    @JoinColumn ( name = "tutor_id" )
    private TutorModel tutor;

}
