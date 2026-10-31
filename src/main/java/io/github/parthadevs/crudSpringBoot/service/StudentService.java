package io.github.parthadevs.crudSpringBoot.service;

import io.github.parthadevs.crudSpringBoot.entity.Student;
import io.github.parthadevs.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // create student
    public Student createStudent(Student student) {
        boolean isEmailExists = this.studentRepository.existsByEmail(student.getEmail());
        if (isEmailExists) {
            throw new RuntimeException("Student with email '" + student.getEmail() + "' already exists!");
        }
        return this.studentRepository.save(student);
    }

    // get student by id
    public Student getStudentById(Long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // get all students
    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    // update student
    public Student updateStudent(Long id, Student student) {
        // Check if student exists, else throw clean exception
        Student studentRes = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Update all relevant fields
        studentRes.setName(student.getName());
        studentRes.setEmail(student.getEmail());
        studentRes.setAge(student.getAge());
        studentRes.setRollNo(student.getRollNo());
        studentRes.setSubject(student.getSubject());

        // Save and return updated entity
        return this.studentRepository.save(studentRes);
    }

    // delete student
    public void deleteStudentById(Long id) {
        if (!this.studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        this.studentRepository.deleteById(id);
    }
}