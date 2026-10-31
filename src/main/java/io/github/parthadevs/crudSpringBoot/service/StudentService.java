package io.github.parthadevs.crudSpringBoot.service;

import io.github.parthadevs.crudSpringBoot.dto.StudentRequestDto;
import io.github.parthadevs.crudSpringBoot.dto.StudentResponceDto;
import io.github.parthadevs.crudSpringBoot.dto.UpdateStudentRequestDto;
import io.github.parthadevs.crudSpringBoot.dto.UpdateStudentResponceDto;
import io.github.parthadevs.crudSpringBoot.entity.Student;
import io.github.parthadevs.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // create student
    public StudentResponceDto createStudent(StudentRequestDto studentRequestDto) {
        Student student = mapToEntity(studentRequestDto);
        boolean isEmailExists = this.studentRepository.existsByEmail(student.getEmail());
        if (isEmailExists) {
            throw new RuntimeException("Student with email '" + student.getEmail() + "' already exists!");
        }
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        student.setDeleted(false);
        Student studentRes = this.studentRepository.save(student);
        return mapToDto(studentRes);
    }

    // get student by id (With Exception Handling)
    public StudentResponceDto getStudentById(Long id) {
        Student student = this.studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return mapToDto(student);
    }

    // get all active students (Soft delete checked)
    public List<StudentResponceDto> getAllStudent() {
        List<Student> studentslist = this.studentRepository.findAllByDeletedIsFalse();
        return studentslist.stream()
                .map(this::mapToDto)
                .toList();
    }

    // update student
    public UpdateStudentResponceDto updateStudent(Long id, UpdateStudentRequestDto updateStudentRequestDto) {
        Student existingStudent = this.studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setName(updateStudentRequestDto.getName());
        existingStudent.setAge(updateStudentRequestDto.getAge());
        existingStudent.setRollNo(updateStudentRequestDto.getRollNo());
        existingStudent.setSubject(updateStudentRequestDto.getSubject());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = this.studentRepository.save(existingStudent);
        return mapToUpdateDto(updatedStudent);
    }

    // delete student (Hard delete)
    public boolean deleteStudentById(Long id) {
        if (!this.studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        this.studentRepository.deleteById(id);
        return true;
    }

    // delete student (Soft delete)
    public boolean deleteStudentSofrly(Long id) {
        Student existingStudent = this.studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RuntimeException("Student not found or already deleted with id: " + id));

        existingStudent.setDeleted(true);
        existingStudent.setUpdatedAt(LocalDateTime.now());
        this.studentRepository.save(existingStudent);
        return true;
    }

    // Mapper Helpers
    private Student mapToEntity(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        student.setRollNo(studentRequestDto.getRollNo());
        student.setSubject(studentRequestDto.getSubject());
        return student;
    }

    private StudentResponceDto mapToDto(Student student) {
        StudentResponceDto studentResponceDto = new StudentResponceDto();
        studentResponceDto.setId(student.getId());
        studentResponceDto.setName(student.getName());
        studentResponceDto.setEmail(student.getEmail());
        studentResponceDto.setAge(student.getAge());
        studentResponceDto.setRollNo(student.getRollNo());
        studentResponceDto.setSubject(student.getSubject());
        studentResponceDto.setCreatedAt(student.getCreatedAt());
        studentResponceDto.setUpdatedAt(student.getUpdatedAt());
        studentResponceDto.setMessage("Student Retrieved Successfully");
        return studentResponceDto;
    }

    private UpdateStudentResponceDto mapToUpdateDto(Student student) {
        UpdateStudentResponceDto responseDto = new UpdateStudentResponceDto();
        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setEmail(student.getEmail());
        responseDto.setAge(student.getAge());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setUpdatedAt(student.getUpdatedAt());
        responseDto.setMessage("Student Updated Successfully");
        return responseDto;
    }
}