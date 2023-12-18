package net.javademo.employeemanagementsystem.service.impl;

import lombok.AllArgsConstructor;
import net.javademo.employeemanagementsystem.dto.EmployeeDto;
import net.javademo.employeemanagementsystem.entity.Employee;
import net.javademo.employeemanagementsystem.exception.ResourceNotFoundException;
import net.javademo.employeemanagementsystem.mapper.EmployeeMapper;
import net.javademo.employeemanagementsystem.repository.EmployeeRepository;
import net.javademo.employeemanagementsystem.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        return EmployeeMapper.mapToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(("Employee is not found with given id (" + employeeId + ")")));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(("Employee is not found with given id (" + employeeId + ")")));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        return EmployeeMapper.mapToEmployeeDto(employeeRepository.save(employee));
    }
}
