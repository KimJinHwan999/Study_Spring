package com.koreait.Springtest17.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.koreait.Springtest17.dto.StudentForm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

	private static Map<Integer, StudentForm> store = new HashMap<Integer, StudentForm>();
	private static int sequence = 0;

	public StudentForm join(StudentForm studentForm) {
		studentForm.setStudentId(++sequence);
		store.put(studentForm.getStudentId(), studentForm);
		return studentForm;
	}

	public StudentForm findOne(int studentId) {
		return store.get(studentId);
	}

	public StudentForm updateForm(StudentForm studentForm) {
		store.put(studentForm.getStudentId(), studentForm);
		return studentForm;
	}

	public List<StudentForm> findAll() {
		return new ArrayList<StudentForm>(store.values());
	}

//	public StudentForm updateForm(StudentForm studentForm) {
//		return "";
//	}

//	@Override
//	public List<StudentForm> findAll() {
//		return new ArrayList<Member>(store.values());
//	}
}
