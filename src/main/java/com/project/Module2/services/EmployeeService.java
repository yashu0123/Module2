package com.project.Module2.services;

import com.project.Module2.dto.EmployeeDTO;
import com.project.Module2.enitities.EmployeeEnitity;
import com.project.Module2.exceptions.ResourceNotFoundException;
import com.project.Module2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final   ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    public Optional<EmployeeDTO> getEmployeeById(Long id){
        Optional<EmployeeEnitity> enitity = employeeRepository.findById(id);
        return enitity.map(employeeEnitity -> modelMapper.map(employeeEnitity,EmployeeDTO.class));
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

        Boolean IsExits = isExitsByEmployeeId(employeeId);
        if(!IsExits) throw new ResourceNotFoundException("Employee not found with id : "+ employeeId);

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
    public  Boolean isExitsByEmployeeId(Long employeeId)
    {
        return employeeRepository.existsById(employeeId);
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {

        Boolean IsExits = isExitsByEmployeeId(employeeId);
        if(!IsExits)
            return  null;
        EmployeeEnitity employeeEnitity = employeeRepository.findById(employeeId).orElse(null);
        updates.forEach((field,value)->
                {
                    Field fieldToUpdated = ReflectionUtils.getRequiredField(EmployeeEnitity.class , field);
                    fieldToUpdated.setAccessible(true);
                    ReflectionUtils.setField(fieldToUpdated , employeeEnitity , value);
                }
                );
        return modelMapper.map(employeeRepository.save(employeeEnitity) , EmployeeDTO.class);

    }
}
