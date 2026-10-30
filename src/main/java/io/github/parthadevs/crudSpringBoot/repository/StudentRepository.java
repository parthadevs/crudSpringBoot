package io.github.parthadevs.crudSpringBoot.repository;

import io.github.parthadevs.crudSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> { }
