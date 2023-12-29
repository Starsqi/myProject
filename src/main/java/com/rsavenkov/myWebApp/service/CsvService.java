package com.rsavenkov.myWebApp.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.rsavenkov.myWebApp.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Service
public class CsvService {

    private final EmployeeService employeeService;

    @Autowired
    public CsvService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void addEmployeesFromCsv(MultipartFile file) throws IOException, CsvValidationException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader csvReader = new CSVReader(br);
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                String name = line[0];
                String surname = line[1];
                String department = line[2];
                int salary = Integer.parseInt(line[3]);

                Employee employee = new Employee(name, surname, department, salary);
                employeeService.saveEmployee(employee);
            }
        }
    }
}
