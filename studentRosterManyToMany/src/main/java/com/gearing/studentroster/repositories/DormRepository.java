package com.gearing.studentroster.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gearing.studentroster.models.Dorm;

@Repository
public interface DormRepository extends CrudRepository<Dorm, Long> {
	List<Dorm> findAll();
	
	Optional<Dorm> findById(Long id);
	
	void deleteById(Long id);
}
