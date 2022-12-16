package com.koreait.Springtest17.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreait.Springtest17.dto.StudentForm;
import com.koreait.Springtest17.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	// ------------ 학생 등록 ------------
	public int add(StudentForm studentForm) {
		
		studentRepository.join(studentForm);
		return studentForm.getStudentId();
		
	}

	public StudentForm find(int studentId) {
		
		return studentRepository.findOne(studentId);
	}

	public List<StudentForm> findAll() {
		
		return studentRepository.findAll();
	}
	
	public StudentForm update(StudentForm studentForm) {
		studentRepository.updateForm(studentForm);
		return studentForm;
	
	}

}
