package com.example.demo.controller;

import com.example.demo.model.Employees;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/add")
    public Employees addEmployee(@RequestBody Employees employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/update")
    public Employees updateEmployee(@RequestBody Employees employee) {
        return employeeService.updateEmployee(employee);
    }
}
