package com.project.Module2.controllers;

import com.project.Module2.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeControllers {

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployeeDetails(@PathVariable(required = false) Long employeeId){
        return new EmployeeDTO("yashwant" , "90990" , employeeId);
    }
    @GetMapping("/employee")
    public String getEmployeeDetails(@RequestParam(required = false) String employeeName){
        return "Employee Name is : " + employeeName;

    }
}
