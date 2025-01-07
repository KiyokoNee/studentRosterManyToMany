package com.gearing.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearing.studentroster.models.AvailableClass;
import com.gearing.studentroster.models.Student;
import com.gearing.studentroster.repositories.AvailableClassRepository;
import com.gearing.studentroster.repositories.StudentRepository;

@Service
public class AvailableClassService {
	@Autowired
	private AvailableClassRepository acRepo;
	@Autowired
	private StudentRepository studentRepo;
	
	public List<AvailableClass> getAll(){
		return acRepo.findAll();
	}
	
	public Optional<AvailableClass> getById(Long id) {
		return acRepo.findById(id);
	}
	
	public void create(AvailableClass lesson) {
		acRepo.save(lesson);
	}
	
	public void addStudentToClass(Long studentId, Long classId) {
		Student student = studentRepo.findById(studentId).get();
		AvailableClass currClass = acRepo.findById(classId).get();
		
		currClass.getStudents().add(student);
		acRepo.save(currClass);
	}
	
	public void removeStudentFromClass(Long studentId, Long classId) {
		Student student = studentRepo.findById(studentId).get();
		AvailableClass currClass = acRepo.findById(classId).get();
		
		currClass.getStudents().remove(student);
		acRepo.save(currClass);
	}
	
	public List<AvailableClass> allByStudent(Student student) {
		return acRepo.findAllByStudents(student);
	}
	
	public List<AvailableClass> allClassesNotTiedTo(Student student) {
		return acRepo.findByStudentsNotContains(student);
	}
}
