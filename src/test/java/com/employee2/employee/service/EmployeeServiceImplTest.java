package com.employee2.employee.service;

import com.employee2.employee.exceptions.EmployeeAlreadyAddedException;
import com.employee2.employee.exceptions.EmployeeNotFoundException;
import com.employee2.employee.exceptions.InvalidInputException;
import com.employee2.employee.exceptions.StorageIsFullException;
import com.employee2.employee.model.Employee;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl out;

    private final Employee FIRST_EMPLOYEE = new Employee("David", "Mikelangelo", 5, 300000);
    private final Employee SECOND_EMPLOYEE = new Employee("Boris", "Godunov", 2, 150000);
    private final Employee THIRD_EMPLOYEE = new Employee("Lara", "Croft", 4, 200000);


    @BeforeEach
    public void createCollection() {
        out = new EmployeeServiceImpl();

    }

    @Test
    void shouldAddCorrectly() {
        Employee add = out.add(
                FIRST_EMPLOYEE.getFirstName(),
                FIRST_EMPLOYEE.getLastName(),
                FIRST_EMPLOYEE.getDepartment(),
                FIRST_EMPLOYEE.getSalary()
        );
        assertEquals(FIRST_EMPLOYEE, add);


        Employee add2 = out.add(
                SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        );
        assertEquals(SECOND_EMPLOYEE, add2);

    }

    @Test
    void shouldThrowExceptionIfEmployeeAlreadyAdded() {
        out.add(SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        );
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.add(SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        ));
    }

    @Test
    void shouldThrowExceptionIfEmployeebookIsFull() {
        out.add(
                FIRST_EMPLOYEE.getFirstName(),
                FIRST_EMPLOYEE.getLastName(),
                FIRST_EMPLOYEE.getDepartment(),
                FIRST_EMPLOYEE.getSalary()
        );
        out.add(
                THIRD_EMPLOYEE.getFirstName(),
                THIRD_EMPLOYEE.getLastName(),
                THIRD_EMPLOYEE.getDepartment(),
                THIRD_EMPLOYEE.getSalary()
        );


        assertThrows(StorageIsFullException.class, () -> out.add("тест", "тест", 1, 2));
    }

    @Test
    void shouldThrowExceptionIfInvalidInput() {
        assertThrows(InvalidInputException.class, () -> out.add("123", "т24", 1, 2));
    }


    @Test
    void shouldThrowExceptionIfThereIsNoSuchEmployeeToRemove() {
        assertThrows(EmployeeNotFoundException.class, () -> out.remove(
                SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary())
        );
    }

    @Test
    void shouldChangeSizeOfListAfterRemoveOrAdd() {
        out.add(SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        );
        assertEquals(6, out.getEmployees().size());
        out.remove(SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        );
        assertEquals(5, out.getEmployees().size());

    }

    @Test
    void shouldThrowExceptionIfThereIsNoSuchEmployeeToFind() {
        assertThrows(EmployeeNotFoundException.class, () -> out.find(
                SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary())
        );

    }

    @Test
    void shouldFindCorrectly() {
        Employee expected = out.add(
                SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        );

        Employee result = out.find(
                SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        );
        assertEquals(expected, result);

    }

    @Test
    void shouldGetEmployees() {
        out.add(
                FIRST_EMPLOYEE.getFirstName(),
                FIRST_EMPLOYEE.getLastName(),
                FIRST_EMPLOYEE.getDepartment(),
                FIRST_EMPLOYEE.getSalary()
        );
        out.add(
                SECOND_EMPLOYEE.getFirstName(),
                SECOND_EMPLOYEE.getLastName(),
                SECOND_EMPLOYEE.getDepartment(),
                SECOND_EMPLOYEE.getSalary()
        );

        Map<String, Employee> expected = Map.of(
                "василий пупкин", new Employee("василий", "пупкин", 1, 55000),
                "Анна Трубецкая", new Employee("Анна", "Трубецкая", 2, 54000),
                "Петр Корабелкин", new Employee("Петр", " Корабелкин", 3, 58000),
                "Ирина Иванова", new Employee("Ирина", "Иванова", 4, 67000),
                "Семен Крупский", new Employee("Семен", "Крупский", 5, 15000),

                FIRST_EMPLOYEE.getFullName(),
                new Employee(FIRST_EMPLOYEE.getFirstName(),
                        FIRST_EMPLOYEE.getLastName(),
                        FIRST_EMPLOYEE.getDepartment(),
                        FIRST_EMPLOYEE.getSalary()),
                SECOND_EMPLOYEE.getFullName(),
                new Employee(
                        SECOND_EMPLOYEE.getFirstName(),
                        SECOND_EMPLOYEE.getLastName(),
                        SECOND_EMPLOYEE.getDepartment(),
                        SECOND_EMPLOYEE.getSalary())
        );
        Collection<Employee> result = out.getEmployees();
        assertEquals( expected.size(), result.size());


    }
}
