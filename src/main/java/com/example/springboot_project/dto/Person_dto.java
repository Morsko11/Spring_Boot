package com.example.springboot_project.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person_dto {
    @NotEmpty
    @Size(min = 3,max = 100)
    private String name;

    @Min(12)
    @Max(120)
    private int age;
}
