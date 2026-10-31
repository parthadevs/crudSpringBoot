package io.github.parthadevs.crudSpringBoot.repository;

import io.github.parthadevs.crudSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    Optional<Student> findByIdAndDeletedIsFalse(Long id);
    List<Student> findAllByDeletedIsFalse();
}
