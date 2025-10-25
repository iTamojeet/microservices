package com.emp.repository;

import com.emp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Supplier<Object> getEmployeesById(Long id);
}
