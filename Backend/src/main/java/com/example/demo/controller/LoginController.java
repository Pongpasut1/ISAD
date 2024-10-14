package com.example.demo.controller;

import com.example.demo.model.Employees;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") // React app
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> addEmployees(@RequestBody Employees employees) {
        try {
            employeeService.saveEmployee(employees);
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding employee.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Employees user) {
        Map<String, String> response = new HashMap<>();

        try {
            Employees existingUser = employeeService.findUserByUsername(user.getUsername());

            if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                response.put("error", "Invalid username or password!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            response.put("message", "Login successful!");
            response.put("role", existingUser.getRole());
            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            response.put("error", "Username not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("error", "Username not found!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
}
