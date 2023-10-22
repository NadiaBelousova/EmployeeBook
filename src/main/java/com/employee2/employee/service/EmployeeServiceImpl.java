package com.employee2.employee.service;

import com.employee2.employee.exceptions.EmployeeAlreadyAddedException;
import com.employee2.employee.exceptions.EmployeeNotFoundException;
import com.employee2.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>(Arrays.asList(
                new Employee("Василий", "Пупкин"),
                new Employee("Анна", "Трубецкая"),
                new Employee("Петр", " Корабелкин"),
                new Employee("Ирина", "Иванова"),
                new Employee("Семен", "Крупский"),
                new Employee("Алексей", "Шабанов"),
                new Employee("Зинаида", "Семенова"),
                new Employee("Давид", "Мартиросян"),
                new Employee("Анастасия", "Турцкевич"),
                new Employee("Лидия", "Разумихина")));


    }
    @Override
    public  Employee add (String firstName,String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeList.add(employee);
        return employee;
    }
    @Override
    public  Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
}

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();

    }
@Override
    public Collection<Employee> getEmployees(String firstName, String lastName) {
        return Collections.unmodifiableList(employeeList);
    }
}