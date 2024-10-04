package com.example.demo.service;

import com.example.demo.model.Attendance;
import com.example.demo.model.AttendanceCriteria;
import com.example.demo.model.EvaluationResults;
import com.example.demo.repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    // นับวันมาสายของพนักงานในช่วงวันที่หนึ่งถึงอีกวันที่หนึ่ง
    public long countLateDays(String employeeId, LocalDate startDate, LocalDate endDate) {
        List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
        return attendances.stream()
                .filter(Attendance::isLate)  // เฉพาะวันที่มาสาย
                .count();
    }

    // นับวันลาของพนักงานในช่วงวันที่หนึ่งถึงอีกวันที่หนึ่ง
    public Map<String, Long> countLeaveDaysByType(String employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate)
                .stream()
                .filter(Attendance::isLeave) // กรองเฉพาะวันที่ลา
                .collect(Collectors.groupingBy(
                        Attendance::getLeaveReason, // แยกประเภทการลา (leaveReason)
                        Collectors.counting() // นับจำนวนวันที่ลาตามแต่ละประเภท
                ));
    }

    public int sumLateMinutes(String employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate)
                .stream()
                .filter(Attendance::isLate) // กรองเฉพาะวันที่มาสาย
                .mapToInt(Attendance::getLateMinutes) // ดึงค่าเวลาที่มาสายในแต่ละวัน
                .sum(); // รวมเวลาที่มาสายทั้งหมด
    }


    public double calculateTotalScore(String employeeId, LocalDate startDate, LocalDate endDate, AttendanceCriteria criteria) {
            // คำนวณคะแนนการลา
            Map<String, Long> leaveDaysByType = countLeaveDaysByType(employeeId, startDate, endDate);
            long personalLeaveDays = leaveDaysByType.getOrDefault("personalLeave", 0L);
            long vacationLeaveDays = leaveDaysByType.getOrDefault("vacationLeave", 0L);
            long sickLeaveDays = leaveDaysByType.getOrDefault("sickLeave", 0L);

            int leaveScore = (personalLeaveDays <= criteria.getMaxPersonalLeaveDays() &&
                    vacationLeaveDays <= criteria.getMaxVacationLeaveDays() &&
                    sickLeaveDays <= criteria.getMaxSickLeaveDays()) ? criteria.getMaxLeaveScore() : 0;

            // คำนวณคะแนนการมาสาย
            int totalLateMinutes = sumLateMinutes(employeeId, startDate, endDate);
            int lateScore = (totalLateMinutes <= criteria.getMaxLateMinutes()) ? criteria.getMaxLateScore() : 0;

            // คะแนนรวมตามเปอร์เซ็นต์ที่กำหนด
            return (leaveScore + lateScore);
    }

}