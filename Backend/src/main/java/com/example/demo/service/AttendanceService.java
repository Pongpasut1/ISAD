package com.example.demo.service;

import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepository;

    // บันทึกการเข้างาน
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // ดึงข้อมูลการเข้างานของพนักงานตาม employeeId และวันที่
    public List<Attendance> getAttendanceByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
    }
}