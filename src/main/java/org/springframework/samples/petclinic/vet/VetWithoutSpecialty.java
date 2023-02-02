package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.model.Person;

import jakarta.persistence.*;

@Entity
@Table(name = "vetsBasic")
public class VetWithoutSpecialty extends Person {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public VetWithoutSpecialty() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
