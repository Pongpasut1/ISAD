package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    private Integer id;

    private String emp_id;

    private int sick_live; //ลาป่วย(ครั้ง)

    private int personal_leave; //ลากิจ(ครั้ง)

    private int annual_leave; //ลาพักร้อน(ครั้ง)

    private int other_leave; //ลาอื่นๆ(ครั้ง)

    private int late_times; //จำนวนครั้งสาย

    private int late_min; //สายรวมกี่นาที


    //getter
    public Integer getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public int getSick_live() {
        return sick_live;
    }

    public int getPersonal_leave() {
        return personal_leave;
    }

    public int getAnnual_leave() {
        return annual_leave;
    }

    public int getOther_leave() {
        return other_leave;
    }

    public int getLate_times() {
        return late_times;
    }

    public int getLate_min() {
        return late_min;
    }

    //setter


    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setSick_live(int sick_live) {
        this.sick_live = sick_live;
    }

    public void setPersonal_leave(int personal_leave) {
        this.personal_leave = personal_leave;
    }

    public void setAnnual_leave(int annual_leave) {
        this.annual_leave = annual_leave;
    }

    public void setOther_leave(int other_leave) {
        this.other_leave = other_leave;
    }

    public void setLate_times(int late_times) {
        this.late_times = late_times;
    }

    public void setLate_min(int late_min) {
        this.late_min = late_min;
    }
}
