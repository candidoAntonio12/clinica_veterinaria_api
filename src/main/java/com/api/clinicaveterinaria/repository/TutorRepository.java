package com.api.clinicaveterinaria.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.clinicaveterinaria.model.TutorModel;

@Repository
public interface TutorRepository extends JpaRepository<TutorModel, UUID> {

}
