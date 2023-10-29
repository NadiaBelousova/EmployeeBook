package com.employee2.employee.service;

import com.employee2.employee.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public List<Employee> getAllByDepartment(int department);
    public Map<Integer, List <Employee>> getAll();

    Employee maxSalary(int department);

    Employee minSalary(int department);
}
