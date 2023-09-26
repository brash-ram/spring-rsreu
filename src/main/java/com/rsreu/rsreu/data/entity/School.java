package com.rsreu.rsreu.data.entity;

import com.rsreu.rsreu.enums.SchoolTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Entity
@Table(name = "schools")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "School_GEN")
    @SequenceGenerator(name = "School_GEN", sequenceName = "School_SEQ", initialValue = 5, allocationSize = 20)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_school", nullable = false)
    private String name;

    @Column(name = "type_school", nullable = false)
    private SchoolTypeEnum type;

    @Max(value = 10000, message = "{validation.numberPlacesStudents}")
    @Min(value = 20, message = "{validation.numberPlacesStudents}")
    @Column(name = "numberPlacesStudents", nullable = false)
    private int numberPlacesStudents;

    @Max(value = 10000, message = "{validation.numberPlacesTeachers}")
    @Min(value = 20, message = "{validation.numberPlacesTeachers}")
    @Column(name = "numberPlacesTeachers", nullable = false)
    private int numberPlacesTeachers;

}
