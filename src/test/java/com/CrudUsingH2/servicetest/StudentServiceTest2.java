package com.CrudUsingH2.servicetest;

import com.CrudUsingH2.dao.StudentDao;
import com.CrudUsingH2.dto.StudentResponseDto;
import com.CrudUsingH2.model.Student;
import com.CrudUsingH2.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class StudentServiceTest2 {
    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;

    public StudentServiceTest2(){
        MockitoAnnotations.openMocks(this);
    }


}
