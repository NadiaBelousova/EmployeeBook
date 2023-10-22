package com.employee2.employee.service;

import com.employee2.employee.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> getEmployees(String firstName, String lastName);
}
