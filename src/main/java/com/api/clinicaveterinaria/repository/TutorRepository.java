package com.api.clinicaveterinaria.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.clinicaveterinaria.model.TutorModel;


public interface TutorRepository extends JpaRepository<TutorModel, UUID> {

}
