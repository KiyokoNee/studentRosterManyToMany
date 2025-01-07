package com.gearing.studentroster.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gearing.studentroster.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	List<Student> findAll();
	
	Optional<Student> findById(Long id);
	
	void deleteById(Long id);
	
	@Query(value = "SELECT * FROM students WHERE dorm_id = :id", nativeQuery = true)
	List<Student> findByDormId(Long id);
}
