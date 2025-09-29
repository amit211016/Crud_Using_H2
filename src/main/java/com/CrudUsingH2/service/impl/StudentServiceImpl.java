package com.CrudUsingH2.service.impl;

import com.CrudUsingH2.dao.StudentDao;
import com.CrudUsingH2.dto.StudentRequestDto;
import com.CrudUsingH2.dto.StudentResponseDto;
import com.CrudUsingH2.dto.StudentUpdateDto;
import com.CrudUsingH2.model.Student;
import com.CrudUsingH2.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentDao studentDao;

    @Override
    public StudentResponseDto findStudentById(Long id) {
        Student student = studentDao.findById(id).get();
        StudentResponseDto studentResponseDto = new StudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getCourse(),
                student.getContact()
        );
        return studentResponseDto;
    }

    @Override
    public List<StudentResponseDto> findAllStudent() {
        List<Student> students = studentDao.findAll();
        System.out.println(students);
        return students.stream().map(student -> new StudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getCourse(),
                student.getContact()
        )).toList();
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        System.out.println(studentRequestDto);
        Student savedStudent =  studentDao.save(modelMapper.map(studentRequestDto, Student.class));
        System.out.println(savedStudent);
        return modelMapper.map(savedStudent, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto updateStudent(StudentUpdateDto studentUpdateDto, Long id) {
        Student student = studentDao.findById(id).get();
        student.setFirstName(studentUpdateDto.getFirstName());
        student.setLastName(studentUpdateDto.getLastName());
        student.setGender(studentUpdateDto.getGender());
        studentDao.save(student);
        StudentResponseDto studentResponseDto = modelMapper.map(student, StudentResponseDto.class);
        return studentResponseDto;
    }

    @Override
    public void deleteStudent(Long id) {
        studentDao.deleteById(id);
    }
}
