package com.gearing.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearing.studentroster.models.Dorm;
import com.gearing.studentroster.repositories.DormRepository;

@Service
public class DormService {
	@Autowired
	private DormRepository dormRepository;
	
	public List<Dorm> allDorms() {
		return dormRepository.findAll();
	}
	
	public Dorm findDorm(Long id) {
		Optional<Dorm> optionalDorm = dormRepository.findById(id);
		return optionalDorm.isPresent() ? optionalDorm.get() : null;
	}
	
	public void createDorm(Dorm dorm) {
		dormRepository.save(dorm);
	}
	
	public void updateDorm(Dorm dorm) {
		dormRepository.save(dorm);
	}
	
	public void deleteDormById(Long id) {
		dormRepository.deleteById(id);
	}
}
