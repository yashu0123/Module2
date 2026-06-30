package com.project.Module2.controllers;

import com.project.Module2.dto.EmployeeDTO;
import com.project.Module2.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class EmployeeControllers {

    private final EmployeeService employeeService;
    public EmployeeControllers(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/employee/{Id}")
    public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable(required = false) Long Id){
        Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeById(Id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());


    }
    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO >> getEmployeeDetails(@RequestParam(required = false) String employeeName){
        List<EmployeeDTO>employees = employeeService.getAllEmployees();

        if(employees==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employees);

    }
    @PostMapping
    public  ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO  employeeEnitity){
        EmployeeDTO saved = employeeService.createNewEmployee(employeeEnitity);
        return new  ResponseEntity<>(saved ,HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long employeeId,EmployeeDTO employeeDTO)
    {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deteleEmployeeById(@PathVariable Long employeeId)
    {
        boolean gotDeleted = employeeService.deteleEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(employeeService.deteleEmployeeById(employeeId));
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId ,@RequestBody Map<String,Object>employeeDTO)
    {
        EmployeeDTO employeeDTO1 = employeeService.updatePartialEmployeeById(employeeId , employeeDTO);
        if(employeeDTO1==null)  return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO1);

    }


}
