package com.api.controller;

import java.util.List;

import com.api.service.StudentService;
import com.api.model.Student;

public class StudentController {
    private final StudentService studentService;

    // Constructor for dependency injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<Student> getAllStudents() {
        try {
            return studentService.getAllStudents();
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log the error)
            e.printStackTrace();
            return null; // Return null or throw a custom exception
        }
    }

    public List<Student> getStudentsByEndpoint(String endpoint) {
        try {
            return studentService.getStudentsByEndpoint(endpoint);
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log the error)
            e.printStackTrace();
            return null; // Return null or throw a custom exception
        }
    }

    public Student createStudent(Student student) {
        try {
            return studentService.createStudent(student);
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log the error)
            e.printStackTrace();
            return null; // Return null or throw a custom exception
        }
    }
}
