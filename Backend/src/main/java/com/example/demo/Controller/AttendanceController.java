package com.example.demo.Controller;

import com.example.demo.Model.Attendance;
import com.example.demo.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // ดึงข้อมูลการเข้างานของพนักงานตาม employeeId และวันที่
    @GetMapping("/employee/{employeeId}/date/{date}")
    public List<Attendance> getAttendance(@PathVariable String employeeId, @PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return attendanceService.getAttendanceByEmployeeIdAndDate(employeeId, parsedDate);
    }
}