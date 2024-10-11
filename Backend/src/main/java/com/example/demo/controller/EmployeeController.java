package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EvaluationResultsService evaluationResultsService;

    @Autowired
    private CriteriaService criteriaService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private SelfEvaluationService selfEvaluationService;


    @PostMapping("/add")
    public Employees addEmployee(@RequestBody Employees employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/update")
    public Employees updateEmployee(@RequestBody Employees employee) {
        return employeeService.updateEmployee(employee);
    }

    @PostMapping("/selfEvaluate")
    public ResponseEntity<?> evaluateEmployee(@RequestBody EvaluationRequest evaluationRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // ตรวจสอบค่า null ของ EvaluationRequest และพิมพ์ค่าออกมาเพื่อตรวจสอบ
            if (evaluationRequest.getCriteriaId() == null ||
                    evaluationRequest.getScores() == null ||
                    evaluationRequest.getStartDate() == null ||
                    evaluationRequest.getEndDate() == null) {
                System.out.println("Invalid input: Missing required fields.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: Missing required fields.");
            }

            Employees employee = employeeService.findUserByUsername(username);
            if (employee != null) {
                String empId = employee.getEmpId();
                Criteria criteria = criteriaService.getCriteriaByCriteriaId(evaluationRequest.getCriteriaId());

                // ตรวจสอบว่า criteria ไม่เป็น null
                if (criteria == null) {
                    System.out.println("Criteria not found for ID: " + evaluationRequest.getCriteriaId());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Criteria not found.");
                }

                Map<String, Integer> scores = evaluationRequest.getScores();
                float totalScore = 0;
                float kpiScore = 0;
                float abilityScore = 0;
                float kpiWeightSum = 0;
                float abilityWeightSum = 0;
                float attendanceScore = 0; // ถ้ามีฟิลด์ attendance_score
                float attendanceWeightSum = 0;

                for (EvaluationCriterion criterion : criteria.getEvaluationCriteria()) {
                    Integer score = scores.get(criterion.getCriterionId());

                    // ตรวจสอบว่า score มีค่า
                    if (score == null) {
                        System.out.println("Score not found for criterion ID: " + criterion.getCriterionId());
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Score missing for criterion: " + criterion.getCriterionId());
                    }

                    if (criterion.getMaxScore() == 0) {
                        System.out.println("Invalid max score for criterion: " + criterion.getCriterionId());
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid max score for criterion.");
                    }

                    float weightedScore = (score / (float) criterion.getMaxScore()) * criterion.getWeight();
                    totalScore += weightedScore;

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
                            //attendanceScore += weightedScore;
                            attendanceWeightSum += criteria.getAttendance_weight();
                            break;
                        default:
                            // จัดการกับประเภทอื่นๆ หากมี
                            break;
                    }
                }

                if (kpiWeightSum > 0) {
                    kpiScore = (kpiScore / kpiWeightSum) * 100;
                }
                if (abilityWeightSum > 0) {
                    abilityScore = (abilityScore / abilityWeightSum) * 100;
                }

                LocalDate startDate = evaluationRequest.getStartDate();
                LocalDate endDate = evaluationRequest.getEndDate();
                attendanceWeightSum += criteria.getAttendance_weight();
                if (criteria != null) {
                    AttendanceCriteria attendanceCriteria = criteria.getAttendance_criteria();
                    System.out.println("Attendance Criteria: " + attendanceCriteria);
                    if (attendanceCriteria != null) {
                        float attendance_max = attendanceCriteria.getMaxLeaveScore() + attendanceCriteria.getMaxLateScore();
                        attendanceScore = (float) attendanceService.calculateTotalScore(employee.getEmpId(), startDate, endDate, attendanceCriteria);

                        if (attendance_max > 0 && attendanceWeightSum > 0) {
                            attendanceScore = (attendanceScore / attendance_max) * attendanceWeightSum;
                        } else {
                            attendanceScore = 0; // กำหนดให้คะแนนเป็น 0 ถ้าค่าน้ำหนักหรือคะแนนเต็มเป็น 0
                        }
                    } else {
                        System.out.println("Attendance criteria is null for the given criteria.");
                    }
                } else {
                    System.out.println("Criteria is null.");
                }

                totalScore += attendanceScore;
                if (attendanceWeightSum > 0) {
                        attendanceScore = (attendanceScore / attendanceWeightSum) * 100; // ปรับเป็นเปอร์เซ็นต์
                    }

                //System.out.println("attendance_max: " + attendance_max);
                System.out.println("attendanceScore: " + attendanceScore);
                System.out.println("attendanceWeightSum: " + attendanceWeightSum);


                SelfEvaluation selfEvaluation = new SelfEvaluation();
                selfEvaluation.setEmpId(empId);
                selfEvaluation.setCriteriaId(evaluationRequest.getCriteriaId());
                selfEvaluation.setScores(evaluationRequest.getScores());
                selfEvaluation.setComment(evaluationRequest.getComment());
                selfEvaluation.setTotal_score(totalScore);
                selfEvaluation.setKPI_score(kpiScore);
                selfEvaluation.setAbility_score(abilityScore);
                selfEvaluation.setAttendance_score(attendanceScore); // ถ้ามี
                selfEvaluation.setEvaluationDate(endDate);

                SelfEvaluation savedEvaluation = selfEvaluationService.saveSelfEvaluation(selfEvaluation);

                if (savedEvaluation != null && savedEvaluation.getId() != null) {
                    return ResponseEntity.ok("Evaluation submitted successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save evaluation.");
                }
            } else {
                System.out.println("Employee not found for username: " + username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the evaluation.");
        }
    }
}
