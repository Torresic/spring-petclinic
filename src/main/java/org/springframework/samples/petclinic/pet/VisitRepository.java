package org.springframework.samples.petclinic.pet;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public interface VisitRepository extends Repository<Visit, Integer> {

	/**
	 * Retrieve all <code>Vet</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Vet</code>s
	 */
	@Transactional(readOnly = true)
	@Cacheable("visits")
	Collection<Visit> findAll() throws DataAccessException;

	/**
	 * Retrieve all <code>Vet</code>s from data store in Pages
	 * @param pageable
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@Cacheable("visits")
	Page<Visit> findAll(Pageable pageable) throws DataAccessException;

	// List<Visit> findByPetId(Integer petId);

	@Query("SELECT v FROM Visit v ORDER BY v.date DESC")
	List<Visit> findLatestVisits();

	void save(Visit visit);

}
