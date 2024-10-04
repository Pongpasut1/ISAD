package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document(collection = "criterias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceCriteria {
    // Getter และ Setter สำหรับทุกฟิลด์
    private int maxLeaveScore; // คะแนนเต็มสำหรับการลา
    private int maxLateScore; // คะแนนเต็มสำหรับการมาสาย
    private int maxSickLeaveDays; //จำนวนวันลาป่วยไม่เกิน
    private int maxPersonalLeaveDays; // จำนวนวันลากิจที่ไม่เกิน
    private int maxVacationLeaveDays; // จำนวนวันลาพักร้อนที่ไม่เกิน
    private int maxLateMinutes; // จำนวนเวลามาสายที่ไม่เกิน

}
