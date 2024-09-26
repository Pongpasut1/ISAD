package com.example.demo.Service;

import com.example.demo.Model.Employees;
import com.example.demo.Repository.EmployeesRepo;
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

    public Employees registerUser(String username, String password) {
        Employees user = new Employees();
        user.setUsername(username);
        user.setPassword(password);
        user.setId(getNextSequenceId("employeeId"));  // Get the next ID
        return employeesRepo.save(user);
    }

    public Employees findUserByUsername(String username) {
        return employeesRepo.findByUsername(username);
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


