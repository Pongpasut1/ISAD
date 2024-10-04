package com.example.demo.controller;

import com.example.demo.model.Attendance;
import com.example.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    // นับวันมาสาย http://localhost:8081 /attendance/employee/07152022/late-days?startDate=2023-10-01&endDate=2023-10-03
    @GetMapping("/employee/{employeeId}/late-days")
    public long getLateDays(@PathVariable String employeeId,
                            @RequestParam LocalDate startDate,
                            @RequestParam LocalDate endDate) {
        return attendanceService.countLateDays(employeeId, startDate, endDate);
    }

    // นับวันลาพร้อมแยกประเภทการลา http://localhost:8081 /attendance/employee/07152022/leave-days?startDate=2023-10-01&endDate=2023-10-03
    @GetMapping("/employee/{employeeId}/leave-days")
    public Map<String, Long> getLeaveDaysByType(@PathVariable String employeeId,
                                                @RequestParam LocalDate startDate,
                                                @RequestParam LocalDate endDate) {
        return attendanceService.countLeaveDaysByType(employeeId, startDate, endDate);
    }

    // หาผลรวมเวลามาสาย http://localhost:8081 /attendance/employee/07152022/late-minutes?startDate=2023-10-01&endDate=2023-10-03
    @GetMapping("/employee/{employeeId}/late-minutes")
    public int getLateMinutes(@PathVariable String employeeId,
                              @RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate) {
        return attendanceService.sumLateMinutes(employeeId, startDate, endDate);
    }

}