package com.CrudUsingH2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentUpdateDto {
    private String firstName;
    private String lastName;
    private String gender;
}
