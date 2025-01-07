package com.gearing.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearing.studentroster.models.Student;
import com.gearing.studentroster.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> allStudents() {
		return studentRepository.findAll();
	}
	
	public Student findStudent(Long id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		return optionalStudent.isPresent() ? optionalStudent.get() : null;
	}
	
	public List<Student> findStudentByDorm(Long id) {
		return studentRepository.findByDormId(id);
	}
	
	public void createStudent(Student student) {
		studentRepository.save(student);
	}
	
	public void updateStudent(Student student) {
		studentRepository.save(student);
	}
	
	public void removeDorm(Student student) {
		student.setDorm(null);
		
		updateStudent(student);
	}
}
