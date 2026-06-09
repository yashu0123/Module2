package com.project.Module2.dto;

public class EmployeeDTO {
    private String name;
    private String phoneNumber;
    private long empId;

    public EmployeeDTO(){

    }
    public EmployeeDTO(String name, String phoneNumber, long empId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.empId = empId;
    }

    // Add getters and setters so Jackson can serialize/deserialize this DTO
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }
}
