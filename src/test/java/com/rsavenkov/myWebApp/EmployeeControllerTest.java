package com.rsavenkov.myWebApp;

import com.rsavenkov.myWebApp.service.CsvService;
import com.rsavenkov.myWebApp.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @MockBean
    private CsvService csvService;

    @Test
    public void testShowAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee("Игорь", "Дунцов", "IT", 500),
                new Employee("Григорий", "Лепс", "HR", 600)
        );

        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("employees", employees))
                .andExpect(MockMvcResultMatchers.view().name("allEmployees"));
    }

    @Test
    public void testAddEmployeeForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("employee"))
                .andExpect(MockMvcResultMatchers.view().name("addEmployee"));
    }

    @Test
    public void testAddEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees/add")
                        .param("name", "John")
                        .param("surname", "Doe")
                        .param("department", "IT")
                        .param("salary", "5000"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/employees"));
    }

    @Test
    public void testAddEmployeesFromCsv() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "John,Doe,IT,5000\nJane,Smith,HR,6000".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/employees/addFromCSV").file(file))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/employees"));
    }
}
