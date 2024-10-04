package com.example.demo.controller;

import com.example.demo.model.Criteria;
import com.example.demo.model.Employees;
import com.example.demo.model.EvaluationResults;
import com.example.demo.model.EvaluationCriterion;
import com.example.demo.model.EvaluationRequest;
import com.example.demo.service.CriteriaService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EvaluationResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hr")
public class HrController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EvaluationResultsService evaluationResultsService;

    @Autowired
    private CriteriaService criteriaService;

    // ตั้งค่าเกณฑ์การประเมิน
    @PostMapping("/setCriteria")
    public void setCriteria(@RequestBody Criteria criteria) {
        criteriaService.saveCriteria(criteria);
    }

    // ประเมินพนักงานโดยใช้ EvaluationRequest
    @PostMapping("/evaluateEmployee")
    public void evaluateEmployee(@RequestBody EvaluationRequest evaluationRequest) {
        Employees employee = employeeService.getEmployeeByEmpId(evaluationRequest.getEmployeeId());
        if (employee != null) {
            Criteria criteria = criteriaService.getCriteriaByCriteriaId(evaluationRequest.getCriteriaId());
            if (criteria != null) {
                Map<String, Integer> scores = evaluationRequest.getScores();
                float totalScore = 0;
                float kpiScore = 0;
                float abilityScore = 0;
                float attendanceScore = 0;
                float kpiWeightSum = 0;
                float abilityWeightSum = 0;
                float attendanceWeightSum = 0;

                // คำนวณคะแนนรวมและคะแนนแต่ละประเภท
                for (EvaluationCriterion criterion : criteria.getEvaluationCriteria()) {
                    int score = scores.getOrDefault(criterion.getCriterionId(), 0);
                    float weightedScore = (score / (float) criterion.getMaxScore()) * criterion.getWeight();
                    totalScore += weightedScore;

                    // เพิ่มคะแนนตามประเภทของเกณฑ์
                    switch (criterion.getType()) {
                        case "KPI":
                            kpiScore += weightedScore;
                            kpiWeightSum += criterion.getWeight();
                            break;
                        case "Ability":
                            abilityScore += weightedScore;
                            abilityWeightSum += criterion.getWeight();
                            break;
                        case "Attendance":
                            attendanceScore += weightedScore;
                            attendanceWeightSum += criterion.getWeight();
                            break;
                    }
                }

                // ปรับคะแนนให้เป็นเปอร์เซ็นต์ถ้าจำเป็น
                if (kpiWeightSum > 0) {
                    kpiScore = (kpiScore / kpiWeightSum) * 100;
                }
                if (abilityWeightSum > 0) {
                    abilityScore = (abilityScore / abilityWeightSum) * 100;
                }
                if (attendanceWeightSum > 0) {
                    attendanceScore = (attendanceScore / attendanceWeightSum) * 100;
                }

                // สร้างผลการประเมิน
                EvaluationResults evaluationResults = new EvaluationResults();
                evaluationResults.setEmpId(evaluationRequest.getEmployeeId());
                evaluationResults.setCriteriaId(evaluationRequest.getCriteriaId());
                evaluationResults.setScores(scores);
                evaluationResults.setTotal_score(totalScore);
                evaluationResults.setKPI_score(kpiScore);
                evaluationResults.setAbility_score(abilityScore);
                evaluationResults.setAttendance_score(attendanceScore);
                evaluationResults.setComment(evaluationRequest.getComment());

                // บันทึกผลการประเมิน
                evaluationResultsService.createEvaluationResult(evaluationResults);
            } else {
                throw new RuntimeException("ไม่พบเกณฑ์การประเมินที่มี criteriaId " + evaluationRequest.getCriteriaId());
            }
        } else {
            throw new RuntimeException("ไม่พบพนักงานที่มี empId " + evaluationRequest.getEmployeeId());
        }
    }

    // จัดการข้อมูลพนักงาน
    @GetMapping("/manageData")
    public List<Employees> manageData() {
        return employeeService.getAllEmployees();
    }
}
