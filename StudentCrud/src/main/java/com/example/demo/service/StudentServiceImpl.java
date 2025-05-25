package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {


	@Autowired
	private StudentRepository repository;

	private StudentDTO toDTO(Student student) {
		return new StudentDTO( student.getName(), student.getCourse(), student.getAge());

	}

	private Student toEntity(StudentDTO dto) {
		return new Student(null, dto.getName(), dto.getCourse(), dto.getAge());
	}

	@Override
	public StudentDTO createStudent(StudentDTO dto) {
		System.out.println("create successfully.....");
		Student saved = repository.save(toEntity(dto));
		return toDTO(saved);
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Override
	public StudentDTO getStudentById(Long id) {
		Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
		return toDTO(student);
	}

	@Override
	public StudentDTO updateStudent(Long id, StudentDTO dto) {
		Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found "));
		student.setName(dto.getName());
		student.setCourse(dto.getCourse());
		student.setAge(dto.getAge());
		return toDTO(repository.save(student));
	}

	@Override
	public void deleteStudent(Long id) {
		repository.deleteById(id);
	}
}
