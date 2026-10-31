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

    // 1. Create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = this.studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // 2. Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = this.studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // 3. Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allStudents = this.studentService.getAllStudent();
        return ResponseEntity.ok(allStudents);
    }

    // 4. Update student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = this.studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // 5. Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student deleted successfully with id: " + id);
    }
}