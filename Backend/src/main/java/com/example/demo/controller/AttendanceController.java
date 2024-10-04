package com.example.demo.controller;

import com.example.demo.model.Attendance;
import com.example.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // บันทึกการเข้างาน
    @PostMapping("/add")
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getAttendance(@PathVariable String employeeId) {
        return attendanceService.getAttendanceByEmployeeId(employeeId);
    }

    // ดึงข้อมูลการเข้างานของพนักงานตาม employeeId และวันที่
    @GetMapping("/employee/{employeeId}/date/{date}")
    public List<Attendance> getAttendance(@PathVariable String employeeId, @PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return attendanceService.getAttendanceByEmployeeIdAndDate(employeeId, parsedDate);
    }

    @GetMapping("/attendances")
    public List<Attendance> getAllAttendancesSortedByDate() {
        return attendanceService.getAllAttendancesSortedByDate();
    }
}