package com.CrudUsingH2.servicetest;

import com.CrudUsingH2.dao.StudentDao;
import com.CrudUsingH2.dto.StudentRequestDto;
import com.CrudUsingH2.dto.StudentResponseDto;
import com.CrudUsingH2.dto.StudentUpdateDto;
import com.CrudUsingH2.model.Student;
import com.CrudUsingH2.service.impl.StudentServiceImpl;
import com.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindStudentById() {
        Student student = new Student(1L, "Amit", "Singh", "Male", "Java", "9876543210");
        when(studentDao.findById(1L)).thenReturn(Optional.of(student));

        StudentResponseDto response = studentService.findStudentById(1L);

        assertEquals(1L, response.getId());
        assertEquals("Amit", response.getFirstName());
        verify(studentDao, times(1)).findById(1L);
    }

    @Test
    void testFindAllStudent() {
        Student s1 = new Student(1L, "Amit", "Singh", "Male", "Java", "9876543210");
        Student s2 = new Student(2L, "Rahul", "Kumar", "Male", "Python", "9999999999");
        when(studentDao.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<StudentResponseDto> response = studentService.findAllStudent();

        assertEquals(2, response.size());
        assertEquals("Rahul", response.get(1).getFirstName());
        verify(studentDao, times(1)).findAll();
    }

    @Test
    void testCreateStudent() {
        StudentRequestDto studentRequestDto = new StudentRequestDto( "Amit", "Singh", "Male", "Java", "9876543210");
        Student saved = new Student(1L, "Amit", "Singh", "Male", "Java", "9876543210");
        StudentResponseDto studentResponseDto = new StudentResponseDto(1L, "Amit", "Singh", "Male", "Java", "9876543210");

        when(modelMapper.map(studentRequestDto, Student.class)).thenReturn(saved);
        when(studentDao.save(saved)).thenReturn(saved);
        when(modelMapper.map(saved, StudentResponseDto.class)).thenReturn(studentResponseDto);


        StudentResponseDto response = studentService.createStudent(studentRequestDto);

        assertEquals(1L, response.getId());
        assertEquals("Amit", response.getFirstName());
        verify(studentDao, times(1)).save(saved);
    }

    @Test
    void testUpdateStudent() {
        Student existing = new Student(1L, "Amit", "Singh", "Male", "Java", "9876543210");
        Student existing1 = new Student(1L, "Amit", "Singh", "Male", "Java", "9876543210");
        StudentResponseDto mappedDto = new StudentResponseDto(1L, "Sumit", "Singh", "Male", "Java", "9876543210");
        StudentUpdateDto updateDto = new StudentUpdateDto( "Sumit", "Singh", "Male");

        when(studentDao.findById(anyLong())).thenReturn(Optional.of(existing));
        when(studentDao.save(existing)).thenReturn(existing);
        when(modelMapper.map(existing, StudentResponseDto.class)).thenReturn(mappedDto);

        StudentResponseDto response = studentService.updateStudent(updateDto,1l);

        assertEquals("Sumit", response.getFirstName());
        assertEquals("Singh", response.getLastName());
        verify(studentDao, times(1)).findById(1L);
        verify(studentDao, times(1)).save(existing);
    }

    @Test
    void testDeleteStudent() {
        Long id = 1L;

        studentService.deleteStudent(id);

        verify(studentDao, times(1)).deleteById(id);
    }

    @Test
    void testPrivateMethodMultiply() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Operation op = new Operation();
        Method method = Operation.class.getDeclaredMethod("subtract", int.class, int.class);
        method.setAccessible(true);
        assertEquals(8, method.invoke(op, 16, 8));
    }
}
