package io.github.parthadevs.crudSpringBoot.controller;

import io.github.parthadevs.crudSpringBoot.entity.Student;
import io.github.parthadevs.crudSpringBoot.service.StudentService;
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
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = this.studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = this.studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allStudents = this.studentService.getAllStudent();
        return ResponseEntity.ok(allStudents);
    }

    // Update student
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = this.studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete student (Hard delete)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean isDeleted = this.studentService.deleteStudentById(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
        }
        return ResponseEntity.ok("Student permanently deleted with id: " + id);
    }

    // Soft Delete Student
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id) {
        Boolean isDeleted = this.studentService.deleteStudentSofrly(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found or already deleted!");
        }
        return ResponseEntity.ok("Record soft deleted successfully for id: " + id);
    }
}