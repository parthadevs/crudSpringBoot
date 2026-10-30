package io.github.parthadevs.crudSpringBoot.controller;

import io.github.parthadevs.crudSpringBoot.entity.Student;
import io.github.parthadevs.crudSpringBoot.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
      Student createdStudent =   this.studentService.createStudent(student);
      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(createdStudent);
    }

//    // read student
//    @GetMapping
//    public  void getStudent() {
//    }
//
//    // read all student
//    @GetMapping
//    public  void  getAllStudent() {
//    }
//
//    // update student
//    @PutMapping
//    public  void  updateStudent() {
//
//    }
//
//    // delete student
//    @DeleteMapping
//    public  void deleteStudent() {
//    }
}
