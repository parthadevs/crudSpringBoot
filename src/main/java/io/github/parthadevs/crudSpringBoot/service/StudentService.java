package io.github.parthadevs.crudSpringBoot.service;

import io.github.parthadevs.crudSpringBoot.entity.Student;
import io.github.parthadevs.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
       Student studentRes =  this.studentRepository.save(student);
        return studentRes;
    }

    public  Student getStudentById(Long id){
        return this.studentRepository.findById(id).get();
    }

}
