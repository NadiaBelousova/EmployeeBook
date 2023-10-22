package com.employee2.employee.model;

import java.util.Objects;

public class Employee {
    String festName;
    String lastName;

    public Employee(String festName, String lastName) {
        this.festName = festName;
        this.lastName = lastName;
    }

    public String getFestName() {
        return festName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(festName, employee.festName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(festName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "festName='" + festName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

