package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.common.MessageConstants;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.InternalServerErrorException;
import com.example.demo.repo.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;
	
	@Override
	public Page<StudentDTO> getStudentsPaginated(Pageable pageable) {
	    Page<Student> studentPage = repository.findAll(pageable);
	    
	    List<StudentDTO> dtoList = studentPage.getContent()
	            .stream()
	            .map(this::toDTO)
	            .collect(Collectors.toList());

	    return new PageImpl<>(dtoList, pageable, studentPage.getTotalElements());
	}

	private StudentDTO toDTO(Student student) {
		return new StudentDTO(student.getName(), student.getCourse(), student.getAge());
	}

	private Student toEntity(StudentDTO dto) {
		return new Student(null, dto.getName(), dto.getCourse(), dto.getAge());
	}

	@Override
	public StudentDTO createStudent(StudentDTO dto) {
		if (dto.getName() == null || dto.getName().isBlank()) {
			throw new BadRequestException("Name is required.");
		}

		try {
			Student saved = repository.save(toEntity(dto));
			return toDTO(saved);
		} catch (Exception ex) {
			throw new InternalServerErrorException("Failed to create student.");
		}
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		List<Student> students = repository.findAll();

		if (students.isEmpty()) {
			throw new BadRequestException(MessageConstants.DATA_NOT_AVAILABLE);
		}

		return students.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Override
	public StudentDTO getStudentById(Long id) {
		Student student = repository.findById(id)
				.orElseThrow(() -> new BadRequestException("Student with ID " + id + " not found."));
		return toDTO(student);
	}

	@Override
	public StudentDTO updateStudent(Long id, StudentDTO dto) {
		Student student = repository.findById(id)
				.orElseThrow(() -> new BadRequestException("Student with ID " + id + " not found."));

		if (dto.getName() == null || dto.getName().isBlank()) {
			throw new BadRequestException("Name cannot be blank.");
		}

		try {
			student.setName(dto.getName());
			student.setCourse(dto.getCourse());
			student.setAge(dto.getAge());
			return toDTO(repository.save(student));
		} catch (Exception ex) {
			throw new InternalServerErrorException("Failed to update student.");
		}
	}

	@Override
	public void deleteStudent(Long id) {
		Student student = repository.findById(id)
				.orElseThrow(() -> new BadRequestException("Student with ID " + id + " not found."));
		try {
			repository.delete(student);
		} catch (Exception ex) {
			throw new InternalServerErrorException("Failed to delete student.");
		}
	}
}
