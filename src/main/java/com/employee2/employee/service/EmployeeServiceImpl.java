package com.employee2.employee.service;

import com.employee2.employee.exceptions.EmployeeAlreadyAddedException;
import com.employee2.employee.exceptions.EmployeeNotFoundException;
import com.employee2.employee.exceptions.InvalidInputException;
import com.employee2.employee.exceptions.StorageIsFullException;
import com.employee2.employee.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final int rangeMax = 7;

    final Map<String, Employee> employeeBook;

    public EmployeeServiceImpl() {
        this.employeeBook = new HashMap<>(Map.of(
                "василий пупкин", new Employee("василий", "пупкин", 1, 55000),
                "Анна Трубецкая", new Employee("Анна", "Трубецкая", 2, 54000),
                "Петр Корабелкин", new Employee("Петр", " Корабелкин", 3, 58000),
                "Ирина Иванова", new Employee("Ирина", "Иванова", 4, 67000),
                "Семен Крупский", new Employee("Семен", "Крупский", 5, 15000)));


    }

    private boolean check(String firstName, String lastName) {
        return StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName);
    }


    @Override
    public Employee add(String firstName, String lastName, int department, int salary) {
        if (!check(firstName, lastName)) {
            throw new InvalidInputException();
        }

        if (employeeBook.size() >= rangeMax) {
            throw new StorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeBook.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, int department, int salary) {
        if (!check(firstName, lastName)) {
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(employee.getFullName())) {
            return employeeBook.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName, int department, int salary) {
        if (!check(firstName, lastName)) {
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(employee.getFullName())) {
            return employeeBook.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();

    }


    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection( employeeBook.values());
    }


}