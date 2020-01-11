package com.springboot.test.service.impl;

import com.springboot.test.model.Student;
import com.springboot.test.repository.StudentRepository;
import com.springboot.test.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Transactional
    public Student create(Student student){
        return studentRepository.save(student);
    }

    public List<Student> all(){
        return studentRepository.findAll();
    }

    @Override
    public Student fetchByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName).orElse(null);
    }

    @Override
    public Student fetchByLastName(String lastName) {
        return studentRepository.findByLastName(lastName).orElse(null);
    }
}
