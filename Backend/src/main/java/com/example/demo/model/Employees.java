package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    public static final String SEQUENCE_NAME = "employees_sequence";

    //setter
    //getter
    @Setter
    @Getter
    @Id
    private Long id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String role;

    private String empId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String surname;

    @Setter
    @Getter
    private String dob;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String phoneNumber;

    @Setter
    @Getter
    private String department;


    public String getEmp_id() {
        return empId;
    }


    public void setEmp_id(String emp_id){
        this.empId = empId;
    }


}
