package com.springboot.test.controller;

import com.springboot.test.model.Student;
import com.springboot.test.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/students")
public class StudentController {
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return  ResponseEntity.ok(studentService.create(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>>allStudents(){
        return  ResponseEntity.ok().body(studentService.all());
    }

    @GetMapping(value = "/firstName")
    public ResponseEntity<Student> fetchByFirstName(@RequestParam("firstName") String firstName){
        return ResponseEntity.ok().body(studentService.fetchByFirstName(firstName));
    }

    @GetMapping(value = "/{lastName}")
    public ResponseEntity<Student> fetchByLastName(@PathVariable("lastName") String lastName){
        return ResponseEntity.ok().body(studentService.fetchByLastName(lastName));
    }
}
