package com.api.clinicaveterinaria.model;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class TutorModel implements Serializable{

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private UUID id;
    @Column( nullable = false )
    private String name;
    @Column( nullable = false )
    private String telephone;
    @Column( nullable = false )
    private String email;
    @Column
    private String address;
    @OneToMany( mappedBy = "tutor" , cascade = CascadeType.ALL )
    private List< AnimalModel > animals;

    public TutorModel() {
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AnimalModel> getAnimals() {
        return this.animals;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TutorModel)) {
            return false;
        }
        TutorModel tutorModel = (TutorModel) o;
        return Objects.equals(id, tutorModel.id) && Objects.equals(name, tutorModel.name) && Objects.equals(telephone, tutorModel.telephone) && Objects.equals(email, tutorModel.email) && Objects.equals(address, tutorModel.address);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, telephone, email, address);
    }
}
