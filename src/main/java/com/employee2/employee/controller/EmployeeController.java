package com.employee2.employee.controller;

import com.employee2.employee.model.Employee;
import com.employee2.employee.service.EmployeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping( "/employee")
public class EmployeeController {
    private final EmployeeServiceImpl service;

    public EmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }
@GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return service.add(firstName, lastName);
    }
    @GetMapping(path = "/remove")
    public Employee removeEmployee (@RequestParam String firstName, @RequestParam String lastName) {
        return service.remove(firstName, lastName);
    }
    @GetMapping(path = "/find")
    public Employee findEmployee (@RequestParam String firstName, @RequestParam String lastName) {
        return service.find(firstName, lastName);
    }
    @GetMapping( path = "/collection")
    public Collection <Employee> getCollection (@RequestParam String firstName, @RequestParam String lastName) {
        return service.getEmployees(firstName, lastName);
    }
}
