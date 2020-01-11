package com.springboot.test.service;

import com.springboot.test.model.Student;

import java.util.List;

public interface StudentService {
    public Student create(Student student);
    public List<Student> all();
    public Student fetchByFirstName(String firstName);
    public Student fetchByLastName(String lastName);
}
