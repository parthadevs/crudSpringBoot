package io.github.parthadevs.crudSpringBoot.controller;

import io.github.parthadevs.crudSpringBoot.dto.StudentRequestDto;
import io.github.parthadevs.crudSpringBoot.dto.StudentResponceDto;
import io.github.parthadevs.crudSpringBoot.dto.UpdateStudentRequestDto;
import io.github.parthadevs.crudSpringBoot.dto.UpdateStudentResponceDto;
import io.github.parthadevs.crudSpringBoot.entity.Student;
import io.github.parthadevs.crudSpringBoot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create student
    @PostMapping("/create")
    public ResponseEntity<StudentResponceDto> createStudent(@Valid @RequestBody StudentRequestDto student) {
        StudentResponceDto createdStudent = this.studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponceDto> getStudentById(@PathVariable Long id) {
        StudentResponceDto student = this.studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<StudentResponceDto>> getAllStudents() {
        List<StudentResponceDto> allStudents = this.studentService.getAllStudent();
        return ResponseEntity.ok(allStudents);
    }

    // Update student
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponceDto> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequestDto student) {
        UpdateStudentResponceDto updatedStudent = this.studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete student (Hard delete)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student permanently deleted with id: " + id);
    }

    // Soft Delete Student
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudentSofrly(id);
        return ResponseEntity.ok("Record soft deleted successfully for id: " + id);
    }
}