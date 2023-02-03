package org.springframework.samples.petclinic.vet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VetWithoutSpecialtyRepository extends JpaRepository<VetWithoutSpecialty, Integer> {

}
