package com.example.demo.service;

import com.example.demo.model.Employees;
import com.example.demo.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepo employeesRepo; // ลบ static ออก

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveEmployee(Employees employee) {
        employeesRepo.save(employee);
    }

    public Employees registerUser(String username, String password, String role) {
        Employees user = new Employees();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setId(getNextSequenceId("employeeId"));  // Get the next ID
        return employeesRepo.save(user);
    }

    public Employees updateEmployee(Employees employee) {
        // ตรวจสอบว่าพนักงานมีอยู่หรือไม่
        Employees existingEmployee = employeesRepo.findById(employee.getId()).orElse(null);
        if (existingEmployee != null) {
            // อัปเดต
            existingEmployee.setUsername(employee.getUsername());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setEmp_id(employee.getEmp_id());
            existingEmployee.setName(employee.getName());
            existingEmployee.setSurname(employee.getSurname());
            existingEmployee.setDOB(employee.getDOB());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhone_number(employee.getPhone_number());
            existingEmployee.setDepartment(employee.getDepartment());
            // บันทึกพนักงานที่ถูกอัปเดต
            return employeesRepo.save(existingEmployee);
        } else {
            // หากไม่พบพนักงาน ให้จัดการตามที่เหมาะสม
            throw new RuntimeException("Employee not found with id " + employee.getId());
        }
    }

    public Employees findUserByUsername(String username) {
        Employees user = employeesRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
    public class UsernameNotFoundException extends RuntimeException {
        public UsernameNotFoundException(String message) {
            super(message);
        }
    }


    // Method to get the next sequence ID
    private synchronized int getNextSequenceId(String sequenceName) {
        // Assuming you have a collection named "counters" with a document structure for sequences
        Counter counter = mongoTemplate.findAndModify(
                query(where("_id").is(sequenceName)),
                new Update().inc("seq", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                Counter.class
        );
        return counter != null ? counter.getSeq() : 1; // Return the next ID or 1 if no counter exists
    }
}

