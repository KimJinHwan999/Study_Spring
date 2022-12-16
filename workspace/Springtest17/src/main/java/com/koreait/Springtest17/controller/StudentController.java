package com.koreait.Springtest17.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.Springtest17.dto.StudentForm;
import com.koreait.Springtest17.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	// --------------- 학생 등록 ---------------
	@GetMapping("studentAddForm")
	public String studentForm(Model model) {
		StudentForm studentForm = new StudentForm();
		model.addAttribute("studentForm", studentForm);
		return "studentAddForm";
	}
	
	@GetMapping("students")
	public String cancel(Model model) {
		model.addAttribute("studentForm", studentService.findAll());
		return "students";
	}
	
	@PostMapping("student")
	public String studentAdd(@ModelAttribute StudentForm studentForm,
							Model model) {
		
		studentService.add(studentForm);
		model.addAttribute("studentForm", studentForm);
		
		return "student";
	}
	// --------------- 학생 등록 끝 ---------------
	
	
	// --------------- 학생 수정 ---------------
	@GetMapping("studentEditForm/{studentId}")
	public String studentEditForm(@PathVariable int studentId,
								  Model model) {
		
		StudentForm studentForm = new StudentForm();
		studentForm = studentService.find(studentId);
		model.addAttribute(studentForm);
		
		return "studentEditForm";
	}
	
	
	@PostMapping("studentEditForm/{studentId}")
	public String studentEdit(@PathVariable int studentId,
							  @ModelAttribute StudentForm studentForm,
							  Model model) {
		
		studentService.update(studentForm);
		model.addAttribute("studentForm", studentForm);
		
		return "student";
		
	}
	
	// --------------- 학생 수정 끝 ---------------
	
	
	
	
	
	
	
}
