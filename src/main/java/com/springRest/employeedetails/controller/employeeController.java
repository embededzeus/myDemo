package com.springRest.employeedetails.controller;

import com.springRest.employeedetails.exception.ResourceNotFoundException;
import com.springRest.employeedetails.model.Employee;
import com.springRest.employeedetails.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class employeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // Get All employee details
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Create a new employee
    @PostMapping("/employees")
    public Employee createNote(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get a Single employee
    @GetMapping("/employees/{id}")
    public Employee getNoteById(@PathVariable(value = "id") Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    // Update a employee
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                           @Valid @RequestBody Employee employeeDetails) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employee.setTitle(employeeDetails.getTitle());
        employee.setContent(employeeDetails.getContent());

        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedNote;
    }

    // Delete a employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }
}
