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
public class attendance {
    @Id
    private Integer id;

    private String emp_id;

    private String leave_date;

    private String type;

    private int duration;

    //getter
    public Integer getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getLeave_date() {
        return leave_date;
    }

    public String getType(){
        return type;
    }

    public int getDuration(){
        return duration;
    }

    //setter


    public void setId(Integer id) {
        this.id = id;
    }

     public void setEmp_id(String emp_id){
        this.emp_id = emp_id;
     }

    public void setLeave_date(String leave_date) {
        this.leave_date = leave_date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
