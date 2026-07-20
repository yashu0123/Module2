package com.project.Module2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.Module2.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "Name of the employee cannot be blank")
    private String name;

    @NotNull(message = "Email of employee cannot be blank")
    @Email
    private String email;

    private Long Id;

    @Max(value = 80 , message = "age cannot be grater than 80")
    @Min(value = 18, message = "age cannot be less than 18")
    private Integer age;

    @PastOrPresent(message = "Date of joining must be not me=ore than current date")
    private LocalDate dateOfJoining;


    @NotBlank(message = "role cannot be null")
    @EmployeeRoleValidation
    private String role;

    @NotNull
    @Positive
    @Digits(integer = 6 , fraction = 2 , message = "salary format xxxx.yy")
    @DecimalMax(value = "10000.99")
    @DecimalMin(value =  "100.50")
    private Integer salary;

    @JsonProperty("isActive")
    @AssertTrue
    private Boolean isActive;

}
