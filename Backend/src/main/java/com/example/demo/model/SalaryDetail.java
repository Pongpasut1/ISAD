package com.example.demo.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "salaryDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDetail {
    //getter
    @Id
    private Integer id;
    private String salary_detailID;
    private String emp_id;
    private float base_salary;
    private float bonus;
    private float deductions;

    //setter

}
