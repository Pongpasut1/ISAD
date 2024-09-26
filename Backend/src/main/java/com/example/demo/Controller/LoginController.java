package com.example.demo.Controller;

import com.example.demo.Model.Employees;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Employees registerUser(@RequestBody Employees user) {
        return employeeService.registerUser(user.getUsername(), user.getPassword(), user.getRole());
    }

    @PostMapping("/login")
    public String login(@RequestBody Employees user) {
        try {
            Employees existingUser = employeeService.findUserByUsername(user.getUsername());
            if (existingUser.getPassword().equals(user.getPassword())) {
                return "Login successful!";
            } else {
                return "Invalid username or password!";
            }
        } catch (EmployeeService.UsernameNotFoundException e) {
            return e.getMessage();
        }
    }
}
