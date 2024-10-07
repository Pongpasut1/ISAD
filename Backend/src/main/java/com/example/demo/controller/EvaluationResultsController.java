package com.example.demo.controller;

import com.example.demo.model.EvaluationResults;
import com.example.demo.service.EvaluationResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluationResults")
public class EvaluationResultsController {

    @Autowired
    private EvaluationResultsService evaluationResultsService;

    // สร้างผลการประเมินใหม่
    @PostMapping("/create")
    public EvaluationResults createEvaluationResult(@RequestBody EvaluationResults evaluationResult) {
        return evaluationResultsService.createEvaluationResult(evaluationResult);
    }

    // รับผลการประเมินทั้งหมด
    @GetMapping("/all")
    public List<EvaluationResults> getAllEvaluationResults() {
        return evaluationResultsService.getAllEvaluationResults();
    }

    // รับผลการประเมินโดย ID
    @GetMapping("/{id}")
    public EvaluationResults getEvaluationResultById(@PathVariable Long id) {
        return evaluationResultsService.getEvaluationResultById(id);
    }

    // อัปเดตผลการประเมิน
    @PutMapping("/update")
    public EvaluationResults updateEvaluationResult(@RequestBody EvaluationResults evaluationResult) {
        return evaluationResultsService.updateEvaluationResult(evaluationResult);
    }

    // ลบผลการประเมิน
    @DeleteMapping("/delete/{id}")
    public String deleteEvaluationResult(@PathVariable Long id) {
        evaluationResultsService.deleteEvaluationResult(id);
        return "Deleted EvaluationResult with id " + id;
    }
}
