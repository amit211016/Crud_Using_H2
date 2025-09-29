package com.CrudUsingH2.controllertest;

import com.CrudUsingH2.controller.StudentController;
import com.CrudUsingH2.dto.StudentRequestDto;
import com.CrudUsingH2.dto.StudentResponseDto;
import com.CrudUsingH2.dto.StudentUpdateDto;
import com.CrudUsingH2.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)   // Only load Controller layer
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;   // Fake Postman

    @Mock
    private StudentService studentService; // Fake Service layer

    // 1. GET student by id
    @Test
    void testGetStudentById() throws Exception {
        StudentResponseDto responseDto =
                new StudentResponseDto(1L, "Amit", "Singh", "Male", "Java", "9876543210");
        when(studentService.findStudentById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Amit"))
                .andExpect(jsonPath("$.lastName").value("Singh"));
    }

    // 2. CREATE student (POST)
    @Test
    void testCreateStudent() throws Exception {
        StudentResponseDto responseDto =
                new StudentResponseDto(1L, "Amit", "Singh", "Male", "Java", "9876543210");

        when(studentService.createStudent(any(StudentRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/student/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Amit\",\"lastName\":\"Singh\",\"gender\":\"Male\",\"course\":\"Java\",\"contact\":\"9876543210\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    // 3. DELETE student
    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk());
    }

    // 4. GET all students
    @Test
    void testGetAllStudents() throws Exception {
        List<StudentResponseDto> students = Arrays.asList(
                new StudentResponseDto(1L, "Amit", "Singh", "Male", "Java", "9876543210"),
                new StudentResponseDto(2L, "Ravi", "Kumar", "Male", "Python", "9876500000")
        );

        when(studentService.findAllStudent()).thenReturn(students);

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Amit"))
                .andExpect(jsonPath("$[1].course").value("Python"));
    }

    // 5. UPDATE student
    @Test
    void testUpdateStudent() throws Exception {
        StudentResponseDto responseDto =
                new StudentResponseDto(1L, "Amit", "Singh", "Male", "Spring", "9876543210");

        when(studentService.updateStudent(any(StudentUpdateDto.class), eq(1L))).thenReturn(responseDto);

        mockMvc.perform(put("/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"course\":\"Spring\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.course").value("Spring"));
    }
}
