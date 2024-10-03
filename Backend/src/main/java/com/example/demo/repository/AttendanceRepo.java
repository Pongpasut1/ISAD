package com.example.demo.repository;

import com.example.demo.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;


public interface AttendanceRepo extends MongoRepository<Attendance, ObjectId> {
    // ค้นหา attendance ของพนักงานตาม employeeId และวันที่
    List<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date);
}