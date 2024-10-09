package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AttendanceService;
import com.example.demo.service.CriteriaService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EvaluationResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    private AttendanceService attendanceService;

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
                    }
                }

                // ปรับคะแนนให้เป็นเปอร์เซ็นต์ถ้าจำเป็น
                if (kpiWeightSum > 0) {
                    kpiScore = (kpiScore / kpiWeightSum) * 100;
                }
                if (abilityWeightSum > 0) {
                    abilityScore = (abilityScore / abilityWeightSum) * 100;
                }
                // ใช้ startDate และ endDate จาก EvaluationRequest
                LocalDate startDate = evaluationRequest.getStartDate();
                LocalDate endDate = evaluationRequest.getEndDate();
                AttendanceCriteria attendanceCriteria = criteria.getAttendance_criteria();

                // คำนวณ attendance score
                attendanceScore = (float) attendanceService.calculateTotalScore(employee.getEmpId(), startDate, endDate, attendanceCriteria);
                float attendance_max = criteria.getAttendance_criteria().getMaxLeaveScore() + criteria.getAttendance_criteria().getMaxLateScore();
                attendanceWeightSum += criteria.getAttendance_weight();
                if (attendance_max > 0 && attendanceWeightSum > 0) {
                    attendanceScore = (attendanceScore / attendance_max) * attendanceWeightSum;
                } else {
                    attendanceScore = 0; // กำหนดให้คะแนนเป็น 0 ถ้าค่าน้ำหนักหรือคะแนนเต็มเป็น 0
                }
                totalScore += attendanceScore;

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

    @GetMapping("/employees/evaluations")
    public List<EmployeeEvaluationDTO> getEmployeesWithEvaluations() {
        List<EvaluationResults> evaluationResults = evaluationResultsService.getAllEvaluationResultsSortedByTotalScoreDesc();
        List<EmployeeEvaluationDTO> employeeEvaluations = new ArrayList<>();

        for (EvaluationResults result : evaluationResults) {
            Employees employee = employeeService.getEmployeeByEmpId(result.getEmpId());
            if (employee != null) {
                EmployeeEvaluationDTO dto = new EmployeeEvaluationDTO(
                        employee.getEmpId(),
                        employee.getName(),
                        employee.getSurname(),
                        employee.getDepartment(),
                        result.getTotal_score()
                );
                employeeEvaluations.add(dto);
            }
        }

        return employeeEvaluations;
    }
}

