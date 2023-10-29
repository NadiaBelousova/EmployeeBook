package com.employee2.employee.service;

import com.employee2.employee.exceptions.EmployeeNotFoundException;
import com.employee2.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl service;

    public DepartmentServiceImpl(EmployeeServiceImpl service) {
        this.service = service;
    }
    @Override
     public List<Employee> getAllByDepartment (int department) {
        return service.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
     }

    @Override
    public Map<Integer, List <Employee>> getAll() {
        return service.getEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    @Override
    public Employee maxSalary(int department) {
        return service.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);

    }
    @Override
    public Employee minSalary(int department) {
        return service.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);

    }
    }

