package com.CrudUsingH2.controller;

import com.CrudUsingH2.dto.StudentRequestDto;
import com.CrudUsingH2.dto.StudentResponseDto;
import com.CrudUsingH2.dto.StudentUpdateDto;
import com.CrudUsingH2.model.Student;
import com.CrudUsingH2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student/")
public class StudentController {
    @Autowired
    private StudentService studentService;


    //GetById
    @GetMapping("{id}")
    public StudentResponseDto getStudent(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }


    //Create Student
    @PostMapping("save")
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return new ResponseEntity<>(studentService.createStudent(studentRequestDto), HttpStatus.CREATED);
    }

    //deleteById
    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


    //GetAll
    @GetMapping
    public List<StudentResponseDto> getAllStudent() {
        return studentService.findAllStudent();
    }


    //UpdateStudent
    @PutMapping("{id}")
    public StudentResponseDto updateStudent(@RequestBody StudentUpdateDto studentUpdateDto,@PathVariable Long id){
        return studentService.updateStudent(studentUpdateDto,id);
    }
}


///,@PathVariable Long id