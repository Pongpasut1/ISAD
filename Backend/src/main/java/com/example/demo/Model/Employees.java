package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    private Integer id;

    private String username;

    private String password;

    private String Role;

    private String emp_id;

    private String name;

    private String surname;

    private String DOB;

    private String email;

    private String phone_number;


    //getter
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return Role;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }


    //setter
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        Role = role;
    }

    public void setEmp_id(String emp_id){
        this.emp_id = emp_id;
    }

    public void setName(String name){
        this.name = name;
    }


    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setDOB(String DOB){
        this.DOB = DOB;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }
}
