package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "attendances")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    private ObjectId id;

    private String employeeId;

    private LocalDate date; // วันที่

    private LocalDateTime checkInTime; // เวลาเข้างาน

    private LocalDateTime checkOutTime; // เวลาออกงาน

    private boolean isLate; // สถานะว่ามาสายหรือไม่

    private int lateMinutes; // จำนวนเวลาที่มาสาย (นาที)

    private boolean isLeave; // สถานะว่าลาหรือไม่

    private String leaveReason; // สาเหตุการลา (ป่วย, ลากิจ, ลาพักร้อน)

    //getter

    public ObjectId getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public boolean getIsLate() {
        return isLate;
    }

    public int getLateMinutes() {
        return lateMinutes;
    }

    public boolean getIsLeave() {
        return isLeave;
    }

    public String getLeaveReason() {
        return leaveReason;
    }


    //setter


    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setIsLate(boolean late) {
        isLate = late;
    }

    public void setLateMinutes(int lateMinutes) {
        this.lateMinutes = lateMinutes;
    }

    public void setIsLeave(boolean leave) {
        isLeave = leave;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
}
