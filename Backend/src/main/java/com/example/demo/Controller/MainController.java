package com.example.demo.Controller;

import com.example.demo.Model.Employees;
import com.example.demo.Repository.EmployeesRepo;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private EmployeesRepo employeesRepo;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployees")
    public void addEmployees(@RequestBody Employees employees) {
        employeesRepo.save(employees);
    }

    @PostMapping("/updateEmployee")
    public Employees updateEmployee(@RequestBody Employees employee) {
        return employeeService.updateEmployee(employee);
    }

    @PostMapping("/register")
    public Employees registerUser(@RequestBody Employees user) {
        return employeeService.registerUser(user.getUsername(), user.getPassword(), user.getRole()); // ใช้ instance ที่ inject
    }

    @PostMapping("/login")
    public String login(@RequestBody Employees user) {
        Employees existingUser = employeeService.findUserByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }
}
