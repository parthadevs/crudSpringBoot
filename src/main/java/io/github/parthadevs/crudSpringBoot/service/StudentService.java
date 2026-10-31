package io.github.parthadevs.crudSpringBoot.service;

import io.github.parthadevs.crudSpringBoot.entity.Student;
import io.github.parthadevs.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        student.setDeleted(false);
        return this.studentRepository.save(student);
    }

    // get student by id
    public Student getStudentById(Long id) {
        return this.studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // get all students
    public List<Student> getAllStudent() {
        return this.studentRepository.findAllByDeletedIsFalse();
    }

    // update student
    public Student updateStudent(Long id, Student student) {
        Student studentRes = this.studentRepository.findByIdAndDeletedIsFalse(id)
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

    // delete student (Hard delete)
    public boolean deleteStudentById(Long id) {
        if (!this.studentRepository.existsById(id)) return false;
        this.studentRepository.deleteById(id);
        return true;
    }

    // delete student (Soft delete)
    public boolean deleteStudentSofrly(Long id) {
        Optional<Student> existingStudent = this.studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()){
            return false;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);
        this.studentRepository.save(studentToSave);
        return true;
    }
}