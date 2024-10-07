package com.example.demo.service;

import com.example.demo.model.Employees;
import com.example.demo.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepo employeesRepo; // ลบ static ออก

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Employees saveEmployee(Employees employee) {
        if (employee.getId() == null) {
            employee.setId(sequenceGeneratorService.generateSequence(Employees.SEQUENCE_NAME));
        }
        return employeesRepo.save(employee);
    }


    public Employees updateEmployee(Employees employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("ID ของพนักงานไม่สามารถเป็น null ได้");
        }
        Optional<Employees> existingEmployeeOpt = employeesRepo.findById(employee.getId());
        if (existingEmployeeOpt.isPresent()) {
            Employees existingEmployee = existingEmployeeOpt.get();
            // อัปเดตข้อมูลตามที่ต้องการ
            existingEmployee.setUsername(employee.getUsername());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setEmpId(employee.getEmpId());
            existingEmployee.setName(employee.getName());
            existingEmployee.setSurname(employee.getSurname());
            existingEmployee.setDob(employee.getDob());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhoneNumber(employee.getPhoneNumber());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setHiredate(employee.getHiredate());
            existingEmployee.setAddress(employee.getAddress());
            // บันทึกพนักงานที่ถูกอัปเดต
            return employeesRepo.save(existingEmployee);
        } else {
            throw new RuntimeException("ไม่พบพนักงานที่มี id " + employee.getId());
        }
    }

    public Employees findUserByUsername(String username) {
        Employees user = employeesRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
    public static class UsernameNotFoundException extends RuntimeException {
        public UsernameNotFoundException(String message) {
            super(message);
        }
    }


    public List<Employees> getAllEmployees() {
        return employeesRepo.findAll();
    }

    public Employees getEmployeeByEmpId(String empId) {
        return employeesRepo.findByEmpId(empId);
    }
}


