package com.example.demo.service;

import com.example.demo.model.Attendance;
import com.example.demo.model.EvaluationResults;
import com.example.demo.repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepository;


    // บันทึกการเข้างาน
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByEmployeeId(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    // ดึงข้อมูลการเข้างานของพนักงานตาม employeeId และวันที่
    public List<Attendance> getAttendanceByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
    }

    public List<Attendance> getAllAttendancesSortedByDate() {
        return attendanceRepository.findAllByOrderByDateAsc();
    }
}