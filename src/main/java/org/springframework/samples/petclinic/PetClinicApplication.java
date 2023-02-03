/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.samples.petclinic.vet.SpecialtyRepository;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.samples.petclinic.vet.VetWithoutSpecialty;
import org.springframework.samples.petclinic.vet.VetWithoutSpecialtyRepository;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
@ImportRuntimeHints(PetClinicRuntimeHints.class)
public class PetClinicApplication {

	/*
	 * 1. Crear objeto Vet sin Speciality 2. Persistir el objeto Vet en BBDD 3. Consultar
	 * por ID y comprobar que se ha creado correctamente 4. Editar el elemento recién
	 * creado para añadir una Speciality concreta 5. Listar todos los veterinarios
	 * existentes.
	 */

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoVetRepository(VetWithoutSpecialtyRepository vetWithoutSpecialtyRepository) {
		return (args) -> {
			VetWithoutSpecialty vetWithoutSpecialty = new VetWithoutSpecialty();
			vetWithoutSpecialty.setFirstName("John");
			vetWithoutSpecialty.setLastName("Doe");

			vetWithoutSpecialtyRepository.save(vetWithoutSpecialty);

			VetWithoutSpecialty vet = vetWithoutSpecialtyRepository.findById(vetWithoutSpecialty.getId()).get();
			System.out.println("Vet created: " + vet.getFirstName() + " " + vet.getLastName());

		};
	}

}
