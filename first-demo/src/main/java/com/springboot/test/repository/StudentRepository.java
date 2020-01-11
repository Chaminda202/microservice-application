package com.springboot.test.repository;

import com.springboot.test.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByFirstName(String firstName);
    Optional<Student> findByLastName(String lastName);
}
