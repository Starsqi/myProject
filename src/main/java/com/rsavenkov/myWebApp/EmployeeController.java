package com.rsavenkov.myWebApp;

import com.opencsv.exceptions.CsvValidationException;
import com.rsavenkov.myWebApp.service.CsvService;
import com.rsavenkov.myWebApp.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeServiceImpl employeeService;
    private final CsvService csvService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService, CsvService csvService){
        this.employeeService = employeeService;
        this.csvService = csvService;
    }

    @GetMapping
    public String showAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "allEmployees";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable int id, @ModelAttribute Employee updatedEmployee) {
        employeeService.updateEmployee(id, updatedEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @PostMapping("/addFromCSV")
    public String addEmployeesFromCsv(@RequestParam("file") MultipartFile file) {
        try {
            csvService.addEmployeesFromCsv(file);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return "redirect:/employees";
    }
}
