package com.rsreu.rsreu.entity;

import com.rsreu.rsreu.enums.SchoolType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class School {

    private String name;
    private SchoolType type;

    @Max(value = 10000, message = "{validation.numberPlacesStudents}")
    @Min(value = 20, message = "{validation.numberPlacesStudents}")
    private int numberPlacesStudents;

    @Max(value = 10000, message = "{validation.numberPlacesTeachers}")
    @Min(value = 20, message = "{validation.numberPlacesTeachers}")
    private int numberPlacesTeachers;

}
