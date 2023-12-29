package com.rsavenkov.myWebApp.service;

import com.rsavenkov.myWebApp.Employee;
import com.rsavenkov.myWebApp.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сотрудника с id " + id + " не существует"));
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee existingEmployee = getEmployee(id);
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setSurname(updatedEmployee.getSurname());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
