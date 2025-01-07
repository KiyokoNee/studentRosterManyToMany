package com.gearing.studentroster.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gearing.studentroster.models.AvailableClass;
import com.gearing.studentroster.models.Dorm;
import com.gearing.studentroster.models.Student;
import com.gearing.studentroster.services.AvailableClassService;
import com.gearing.studentroster.services.DormService;
import com.gearing.studentroster.services.StudentService;

import jakarta.validation.Valid;

@Controller
public class DormController {
	@Autowired
	private DormService dormService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private AvailableClassService acService;
	
	@GetMapping("/dorms")
	public String allDorms(Model model) {
		List<Dorm> dorms = dormService.allDorms();
		model.addAttribute("dorms", dorms);
		
		return "alldorms.jsp";
	}
	
	@GetMapping("/classes")
	public String allClasses(Model model) {
		List<AvailableClass> classes = acService.getAll();
		
		model.addAttribute("classes", classes);
		return "allclasses.jsp";
	}
	
	@GetMapping("/dorms/new")
	public String dormForm(Model model, @ModelAttribute Dorm dorm) {
		return "dormform.jsp";
	}
	
	@PostMapping("/dorms/new")
	public String dormFormSubmit(Model model, @Valid @ModelAttribute Dorm dorm, BindingResult result) {
		if(result.hasErrors())
			return "dormform.jsp";
		
		dormService.createDorm(dorm);
		return "redirect:/dorms";
	}
	
	@GetMapping("/students/new")
	public String studentForm(Model model, @ModelAttribute Student student) {
		List<Dorm> dorms = dormService.allDorms();
		model.addAttribute("dorms", dorms);
		return "studentform.jsp";
	}
	
	@PostMapping("/students/new")
	public String studentFormSubmit(Model model, @Valid @ModelAttribute Student student, BindingResult result) {
		if(result.hasErrors())
			return "studentform.jsp";
		
		studentService.createStudent(student);
		return "redirect:/dorms";
	}
	
	@GetMapping("/classes/new")
	public String classForm(Model model, @ModelAttribute("newClass") AvailableClass newClass) {
		return "classform.jsp";
	}
	
	@PostMapping("/classes/new")
	public String classFormSubmit(Model model, @Valid @ModelAttribute("newClass") AvailableClass newClass, BindingResult result) {
		if(result.hasErrors())
			return "classform.jsp";
		
		acService.create(newClass);
		return "redirect:/classes";
	}
	
	@GetMapping("/dorms/{id}")
	public String dormStudents(Model model, @PathVariable Long id) {
		Dorm dorm = dormService.findDorm(id);
		List<Student> allStudents = studentService.allStudents();
		List<Student> dormStudents = studentService.findStudentByDorm(id);
		
		model.addAttribute("dorm", dorm);
		model.addAttribute("allStudents", allStudents);
		model.addAttribute("dormStudents", dormStudents);
		
		return "dormstudents.jsp";
	}
	
	@GetMapping("/students/{id}")
	public String studentClasses(Model model, @PathVariable Long id) {
		Student student = studentService.findStudent(id);
		List<AvailableClass> unselectedClasses = acService.allClassesNotTiedTo(student);
		List<AvailableClass> selectedClasses = acService.allByStudent(student);
		
		model.addAttribute("student", student);
		model.addAttribute("unselectedClasses", unselectedClasses);
		model.addAttribute("selectedClasses", selectedClasses);
		
		return "studentclasses.jsp";
	}
	
	@GetMapping("/classes/{id}")
	public String classStudents(Model model, @PathVariable Long id) {
		Optional<AvailableClass> currClass = acService.getById(id);
		if(currClass.isEmpty())
			return "redirect:/classes";
		
		List<Student> classStudents = currClass.get().getStudents();
		
		model.addAttribute("lesson", currClass.get());
		model.addAttribute("classStudents", classStudents);
		
		return "classstudents.jsp";
	}
	
	@PutMapping("/dorms/{dormId}/remove/{id}")
	public String removeStudent(@PathVariable Long dormId, @PathVariable Long id) {
		studentService.removeDorm(studentService.findStudent(id));
		
		return "redirect:/dorms/" + dormId;
	}
	
	@PutMapping("/students/{studentId}/remove/{classId}")
	public String removeClass(@PathVariable Long studentId, @PathVariable Long classId) {
		acService.removeStudentFromClass(studentId, classId);
		
		return "redirect:/students/" + studentId;
	}
	
	@PutMapping("/dorms/{dormId}/add")
	public String addStudent(@PathVariable Long dormId, @RequestParam Long studentid) {
		Student student = studentService.findStudent(studentid);
		student.setDorm(dormService.findDorm(dormId));
		
		studentService.updateStudent(student);
		return "redirect:/dorms/" + dormId;
	}
	
	@PutMapping("/students/{studentId}/add")
	public String addClass(@PathVariable Long studentId, @RequestParam Long classId) {
		acService.addStudentToClass(studentId, classId);
		
		return "redirect:/students/" + studentId;
	}
}
