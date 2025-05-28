package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.StudentDTO;

public interface StudentService {
 StudentDTO createStudent(StudentDTO studentDTO);
 List<StudentDTO> getAllStudents();
 StudentDTO getStudentById(Long id);
 StudentDTO updateStudent(Long id, StudentDTO studentDTO);
 void deleteStudent(Long id);
 
 Page<StudentDTO> getStudentsPaginated(Pageable pageable); 
}
