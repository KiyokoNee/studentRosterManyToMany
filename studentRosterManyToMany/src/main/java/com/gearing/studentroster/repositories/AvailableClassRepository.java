package com.gearing.studentroster.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gearing.studentroster.models.AvailableClass;
import com.gearing.studentroster.models.Student;

@Repository
public interface AvailableClassRepository extends CrudRepository<AvailableClass, Long> {
	
	List<AvailableClass> findAll();
	
	Optional<AvailableClass> findById(Long id);
	
	List<AvailableClass> findAllByStudents(Student student);
	
	List<AvailableClass> findByStudentsNotContains(Student student);
}
