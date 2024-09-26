package com.example.demo.Controller;

import com.example.demo.Model.Employees;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public void addEmployees(@RequestBody Employees employees) {
        employeeService.saveEmployee(employees);
    }

    @PostMapping("/update")
    public Employees updateEmployee(@RequestBody Employees employee) {
        return employeeService.updateEmployee(employee);
    }
}
