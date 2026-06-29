package com.project.Module2.controllers;

import com.project.Module2.dto.EmployeeDTO;
import com.project.Module2.enitities.EmployeeEnitity;
import com.project.Module2.repositories.EmployeeRepository;
import com.project.Module2.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeControllers {

    private final EmployeeService employeeService;
    public EmployeeControllers(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/employee/{Id}")
    public EmployeeDTO getEmployeeDetails(@PathVariable(required = false) Long Id){
        return  employeeService.getEmployeeById(Id);
    }
    @GetMapping("/employee")
    public List<EmployeeDTO > getEmployeeDetails(@RequestParam(required = false) String employeeName){
        return employeeService.getAllEmployees();

    }
    @PostMapping
    public  EmployeeDTO  createNewEmployee(@RequestBody EmployeeDTO  employeeEnitity){
        return employeeService.createNewEmployee(employeeEnitity);
    }

    @PutMapping("/{employeeId}")
    public EmployeeDTO updateEmployeeById(Long employeeId,@PathVariable EmployeeDTO employeeDTO)
    {
        return employeeService.updateEmployeeById(employeeId,employeeDTO);
    }
    @DeleteMapping("/{employeeId}")
    public boolean deteleEmployeeById(@PathVariable Long employeeId)
    {

        return employeeService.deteleEmployeeById(employeeId);
    }

}
