package com.employee2.employee.service;

import com.employee2.employee.exceptions.EmployeeNotFoundException;
import com.employee2.employee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    private static final List<Employee> LIST = List.of(
            new Employee("David", "Mikelangelo", 2, 300000),
            new Employee("Boris", "Godunov", 2, 150000),
            new Employee("Lara", "Croft", 4, 200000)

    );
    @Mock
    private EmployeeServiceImpl serviceMock;

    private DepartmentServiceImpl out;

    @BeforeEach
    public void create() {
        when(serviceMock.getEmployees()).thenReturn(LIST);
        out = new DepartmentServiceImpl(serviceMock);


    }

    @Test
    void shouldGetAllByDepartment() {

        List<Employee> expected = List.of(
                new Employee("David", "Mikelangelo", 2, 300000),
                new Employee("Boris", "Godunov", 2, 150000)

        );
        List<Employee> result = out.getAllByDepartment(2);
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0), result.get(0));
        assertEquals(expected.get(1), result.get(1));


        verify(serviceMock, only()).getEmployees();
    }

    @Test
    void shouldGetAll() {
        Map<Integer, List<Employee>> result = out.getAll();
        List<Employee> res1 = result.get(2);
        List<Employee> exp1 = List.of(new Employee("David", "Mikelangelo", 2, 300000),
                new Employee("Boris", "Godunov", 2, 150000));
        assertEquals(exp1.size(), res1.size());
        assertEquals(exp1.get(0), res1.get(0));

        List<Employee> res2 = result.get(4);
        List<Employee> exp2 = List.of(new Employee("Lara", "Croft", 4, 200000));
        assertEquals(exp2.size(), res2.size());
        assertEquals(exp2.get(0), res2.get(0));

        verify(serviceMock, only()).getEmployees();
    }


    @Test
    void shouldCalculateMaxSalaryCorrect() {
        Employee expected = new Employee("David", "Mikelangelo", 2, 300000);
        Employee result = out.maxSalary(2);
        assertEquals(expected,result);
        verify(serviceMock, only()).getEmployees();
    }
    @Test
    void shouldThrowEmployeeNotFoundExceptionInCalculateMaxSalary() {
        assertThrows(EmployeeNotFoundException.class,()->out.maxSalary(5));
    }
    @Test
    void shouldThrowEmployeeNotFoundExceptionInCalculateMinSalary() {
        assertThrows(EmployeeNotFoundException.class,()->out.minSalary(5));
    }


    @Test
    void shouldCalculateMinSalaryCorrect() {
        Employee expected = new Employee("Boris", "Godunov", 2, 150000);
        Employee result = out.minSalary(2);
        assertEquals(expected,result);
        verify(serviceMock, only()).getEmployees();
    }

    @Test
    void shouldCalculateSumSalaryCorrect() {
        int sumExp=450000;
        int sumResult = out.sumSalaryByDepartment(2);
        assertEquals(sumExp,sumResult);
        verify(serviceMock, only()).getEmployees();
    }
}