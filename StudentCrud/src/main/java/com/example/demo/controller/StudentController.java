package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

 @Autowired
 private StudentService studentService;

 @PostMapping
 public StudentDTO createStudent(@RequestBody StudentDTO dto) {
     return studentService.createStudent(dto);
 }

 @GetMapping
 public List<StudentDTO> getAllStudents() {
     return studentService.getAllStudents();
 }

 @GetMapping("/{id}")
 public StudentDTO getStudentById(@PathVariable Long id) {
     return studentService.getStudentById(id);
 }

 @PutMapping("/{id}")
 public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
     return studentService.updateStudent(id, dto);
 }

 @DeleteMapping("/{id}")
 public void deleteStudent(@PathVariable Long id) {
     studentService.deleteStudent(id);
 }
}
