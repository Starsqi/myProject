package com.rsavenkov.myWebApp.service;

import com.rsavenkov.myWebApp.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public Employee getEmployee(int id);

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(int id, Employee updatedEmployee); // нужен ли?
    public void deleteEmployee(int id);
}
