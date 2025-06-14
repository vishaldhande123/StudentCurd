package com.example.demo.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.MessageConstants;
import com.example.demo.common.ResponseModel;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudentServiceImpl;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentService studentService;

    
    @GetMapping("/paginated")
    public ResponseModel getStudentsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentDTO> studentPage = studentService.getStudentsPaginated(pageable);
        logger.info("Success....");
        return new ResponseModel(
                MessageConstants.SUCCESS,
                MessageConstants.SUCCESS_STATUS_CODE,
                "Students fetched with pagination",
                studentPage
        );
    }
    
    // CREATE
    @PostMapping
    public ResponseModel createStudent(@RequestBody StudentDTO dto) {
        StudentDTO created = studentService.createStudent(dto);
        return ResponseModel.ok("Student created successfully", created);
    }

    // READ ALL
    @GetMapping
    public ResponseModel getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseModel.ok("Students fetched successfully", students);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseModel getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseModel.ok("Student fetched successfully", student);
        } else {
            return ResponseModel.error(404, "Student not found with ID: " + id);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseModel updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        StudentDTO updated = studentService.updateStudent(id, dto);
        return ResponseModel.ok("Student updated successfully", updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseModel deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseModel.ok("Student deleted successfully", null);
    }
}