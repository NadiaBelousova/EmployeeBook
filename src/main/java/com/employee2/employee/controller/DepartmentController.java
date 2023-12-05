package com.employee2.employee.controller;

import com.employee2.employee.model.Employee;
import com.employee2.employee.service.DepartmentService;
import com.employee2.employee.service.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAll(@RequestParam(required = false) Integer department) {
        return department == null ?
                ResponseEntity.ok(service.getAll()) :
                ResponseEntity.ok(service.getAllByDepartment(department));
    }

    @GetMapping("/max")
    public Employee maxSalary(@RequestParam Integer department) {
       return service.maxSalary(department);
    }

    @GetMapping("/min")
    public Employee minSalary(@RequestParam Integer department) {
        return service.minSalary(department);
    }

    @GetMapping("/sumSalary")
    public int sumSalaryByDepartment(@RequestParam Integer department) {
        return service.sumSalaryByDepartment(department);
    }
}
