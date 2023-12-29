package com.rsavenkov.myWebApp;

import jakarta.validation.constraints.*;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Surname must contain only letters")
    private String surname;
    @NotBlank(message = "Department is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Department must contain only letters")
    private String department;
    @NotNull(message = "Salary is mandatory")
    @Min(value = 0, message = "Salary must be a non-negative number")
    private int salary;

    public Employee() {
    }

    public Employee(String name, String surname, String department, int salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
