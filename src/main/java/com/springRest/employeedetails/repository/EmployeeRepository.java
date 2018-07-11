package com.springRest.employeedetails.repository;

import com.springRest.employeedetails.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
