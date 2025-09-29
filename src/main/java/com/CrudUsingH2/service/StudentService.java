package com.CrudUsingH2.service;

import com.CrudUsingH2.dto.StudentRequestDto;
import com.CrudUsingH2.dto.StudentResponseDto;
import com.CrudUsingH2.dto.StudentUpdateDto;
import com.CrudUsingH2.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    public abstract StudentResponseDto findStudentById(Long id);
    public abstract List<StudentResponseDto> findAllStudent();
    public abstract StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
    public abstract StudentResponseDto updateStudent(StudentUpdateDto studentUpdateDtoDto, Long id);
    public abstract void deleteStudent(Long id);
}
