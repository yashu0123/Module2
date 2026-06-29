package com.project.Module2.services;

import com.project.Module2.dto.EmployeeDTO;
import com.project.Module2.enitities.EmployeeEnitity;
import com.project.Module2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final   ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEnitity enitity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(enitity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEnitity> employeeEnitities = employeeRepository.findAll();
        List<EmployeeDTO> collect = employeeEnitities
                .stream()
                .map(employeeEnitity -> modelMapper.map(employeeEnitity, EmployeeDTO.class))
                .collect(Collectors.toList());
        return collect;

    }
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO)
    {
        EmployeeEnitity enitity = modelMapper.map(employeeDTO, EmployeeEnitity.class);
        EmployeeEnitity employeeEnitity = employeeRepository.save(enitity);
        return modelMapper.map(employeeEnitity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {

        EmployeeEnitity enitity = modelMapper.map(employeeDTO , EmployeeEnitity.class);
        enitity.setId(employeeId);
        EmployeeEnitity saved  = employeeRepository.save(enitity);
        return modelMapper.map(saved, EmployeeDTO.class);
    }

    public boolean deteleEmployeeById(Long employeeId) {
        boolean IsExists = employeeRepository.existsById(employeeId);
        if(!IsExists)
            return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }
}
