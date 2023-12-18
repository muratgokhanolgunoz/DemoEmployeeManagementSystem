package net.javademo.employeemanagementsystem.service.impl;

import lombok.AllArgsConstructor;
import net.javademo.employeemanagementsystem.dto.EmployeeDto;
import net.javademo.employeemanagementsystem.entity.Employee;
import net.javademo.employeemanagementsystem.exception.ResourceNotFoundException;
import net.javademo.employeemanagementsystem.mapper.EmployeeMapper;
import net.javademo.employeemanagementsystem.repository.EmployeeRepository;
import net.javademo.employeemanagementsystem.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not found with given id"));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
