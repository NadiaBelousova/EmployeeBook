package com.employee2.employee.service;

import com.employee2.employee.exceptions.EmployeeAlreadyAddedException;
import com.employee2.employee.exceptions.EmployeeNotFoundException;
import com.employee2.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employeeBook;

    public EmployeeServiceImpl() {
        this.employeeBook = new HashMap<>(Map.of(
               "Василий Пупкин", new Employee("Василий", "Пупкин"),
              "Анна Трубецкая",  new Employee("Анна", "Трубецкая"),
               "Петр Корабелкин", new Employee("Петр", " Корабелкин"),
               "Ирина Иванова", new Employee("Ирина", "Иванова"),
               "Семен Крупский", new Employee("Семен", "Крупский"),
               "Алексей Шабанов", new Employee("Алексей", "Шабанов"),
               "Зинаила Семеновна", new Employee("Зинаида", "Семенова"),
               "Давид Мартиросян", new Employee("Давид", "Мартиросян"),
                "Анастасия Турцкевич", new Employee("Анастасия", "Турцкевич"),
                "Лидия Разумихина", new Employee("Лидия", "Разумихина")));


    }
    @Override
    public  Employee add (String firstName,String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeBook. containsKey (employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
    }
        employeeBook.put(employee.getFullName(), employee);
        return employee;}
    @Override
    public  Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeBook. containsKey (employee.getFullName())) {
            return employeeBook.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
}

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeBook.containsKey(employee.getFullName())) {
            return employeeBook.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();

    }
@Override
    public Collection<Employee> getEmployees(String firstName, String lastName) {
        return Collections.unmodifiableCollection(employeeBook.values());
    }
}