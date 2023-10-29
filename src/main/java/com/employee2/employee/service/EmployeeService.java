package com.employee2.employee.service;

import com.employee2.employee.model.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int department, int salary);

    Employee remove(String firstName, String lastName, int department, int salary);

    Employee find(String firstName, String lastName, int department, int salary);



    Collection<Employee> getEmployees();
}
