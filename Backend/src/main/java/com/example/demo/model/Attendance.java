package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Document(collection = "attendances")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Getter
    @Id
    private ObjectId id;

    @Getter
    private String employeeId;

    @Getter
    private LocalDate date; // วันที่

    @Getter
    private LocalDateTime checkInTime; // เวลาเข้างาน

    @Getter
    private LocalDateTime checkOutTime; // เวลาออกงาน

    private boolean isLate; // สถานะว่ามาสายหรือไม่

    @Getter
    private int lateMinutes; // จำนวนเวลาที่มาสาย (นาที)

    private boolean isLeave; // สถานะว่าลาหรือไม่

    @Getter
    private String leaveReason; // สาเหตุการลา (ป่วย, ลากิจ, ลาพักร้อน)

    //getter

    public boolean getIsLate() {
        return isLate;
    }

    public boolean getIsLeave() {
        return isLeave;
    }

    public boolean isLate() { return isLate; }

    public boolean isLeave() { return isLeave; }

    //setter


    public void setLate(boolean late) {
        isLate = late;
    }

    public void setLeave(boolean leave) {
        isLeave = leave;
    }
}
