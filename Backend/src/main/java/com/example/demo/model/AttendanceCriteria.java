package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "criterias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceCriteria {
    private int maxLeaveScore; // คะแนนเต็มสำหรับการลา
    private int maxLateScore; // คะแนนเต็มสำหรับการมาสาย
    private int maxSickLeaveDays; //จำนวนวันลาป่วยไม่เกิน
    private int maxPersonalLeaveDays; // จำนวนวันลากิจที่ไม่เกิน
    private int maxVacationLeaveDays; // จำนวนวันลาพักร้อนที่ไม่เกิน
    private int maxLateMinutes; // จำนวนเวลามาสายที่ไม่เกิน

    // Getter และ Setter สำหรับทุกฟิลด์
    public int getMaxLeaveScore() {
        return maxLeaveScore;
    }

    public int getMaxLateScore() {
        return maxLateScore;
    }

    public int getMaxPersonalLeaveDays() {
        return maxPersonalLeaveDays;
    }

    public int getMaxVacationLeaveDays() {
        return maxVacationLeaveDays;
    }

    public int getMaxLateMinutes() {
        return maxLateMinutes;
    }

    public int getMaxSickLeaveDays() {
        return maxSickLeaveDays;
    }

    public void setMaxLeaveScore(int maxLeaveScore) {
        this.maxLeaveScore = maxLeaveScore;
    }

    public void setMaxPersonalLeaveDays(int maxPersonalLeaveDays) {
        this.maxPersonalLeaveDays = maxPersonalLeaveDays;
    }

    public void setMaxVacationLeaveDays(int maxVacationLeaveDays) {
        this.maxVacationLeaveDays = maxVacationLeaveDays;
    }

    public void setMaxLateMinutes(int maxLateMinutes) {
        this.maxLateMinutes = maxLateMinutes;
    }

    public void setMaxLateScore(int maxLateScore) {
        this.maxLateScore = maxLateScore;
    }

    public void setMaxSickLeaveDays(int maxSickLeaveDays) {
        this.maxSickLeaveDays = maxSickLeaveDays;
    }
}
