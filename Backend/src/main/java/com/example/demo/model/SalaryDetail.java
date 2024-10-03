package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "salaryDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDetail {
    @Id
    private Integer id;
    private String salary_detailID;
    private String emp_id;
    private float base_salary;
    private float bonus;
    private float deductions;

    //getter
    public Integer getId(){
        return id;
    }

    public String getSalary_detailID(){
        return salary_detailID;
    }

    public String getEmp_id(){
        return emp_id;
    }

    public float getBase_salary() {
        return base_salary;
    }

    public float getBonus() {
        return bonus;
    }

    public float getDeductions() {
        return deductions;
    }

    //setter

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setSalary_detailID(String salary_detailID) {
        this.salary_detailID = salary_detailID;
    }

    public void setBase_salary(float base_salary) {
        this.base_salary = base_salary;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public void setDeductions(float deductions) {
        this.deductions = deductions;
    }
}
