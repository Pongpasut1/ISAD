package com.example.demo.controller;

import com.example.demo.model.Employees;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") //  React app
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Employees registerUser(@RequestBody Employees user) {
        return employeeService.registerUser(user.getUsername(), user.getPassword(), user.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Employees user) {
        Employees existingUser = employeeService.findUserByUsername(user.getUsername());
        Map<String, String> response = new HashMap<>();

        if (existingUser == null) {
            response.put("error", "The system contains no user data!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else if (!existingUser.getPassword().equals(user.getPassword())) {
            response.put("error", "Invalid username or password!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            response.put("message", "Login successful!");
            return ResponseEntity.ok(response);
        }
    }
}
