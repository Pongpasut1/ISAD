package com.example.demo.service;

import com.example.demo.model.EvaluationResults;
import com.example.demo.repository.EvaluationResultsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationResultsService {

    @Autowired
    private EvaluationResultsRepo evaluationResultsRepo;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    // สร้างผลการประเมินใหม่
    public EvaluationResults createEvaluationResult(EvaluationResults evaluationResult) {
        evaluationResult.setId(sequenceGeneratorService.generateSequence(EvaluationResults.SEQUENCE_NAME));
        return evaluationResultsRepo.save(evaluationResult);
    }

    // รับผลการประเมินทั้งหมด
    public List<EvaluationResults> getAllEvaluationResults() {
        return evaluationResultsRepo.findAll();
    }

    // รับผลการประเมินโดย ID
    public EvaluationResults getEvaluationResultById(Long id) {
        Optional<EvaluationResults> result = evaluationResultsRepo.findById(id);
        return result.orElseThrow(() -> new RuntimeException("EvaluationResult not found with id " + id));
    }

    // อัปเดตผลการประเมิน
    public EvaluationResults updateEvaluationResult(EvaluationResults evaluationResult) {
        if (evaluationResult.getId() == null) {
            throw new IllegalArgumentException("ID ของ EvaluationResult ไม่สามารถเป็น null ได้");
        }
        if (evaluationResultsRepo.existsById(evaluationResult.getId())) {
            return evaluationResultsRepo.save(evaluationResult);
        } else {
            throw new RuntimeException("ไม่พบ EvaluationResult ที่มี id " + evaluationResult.getId());
        }
    }

    // ลบผลการประเมิน
    public void deleteEvaluationResult(Long id) {
        if (evaluationResultsRepo.existsById(id)) {
            evaluationResultsRepo.deleteById(id);
        } else {
            throw new RuntimeException("EvaluationResult not found with id " + id);
        }
    }
    // ดึงผลการประเมินทั้งหมดและจัดเรียงตามคะแนนรวมจากมากไปน้อย
    public List<EvaluationResults> getAllEvaluationResultsSortedByTotalScoreDesc() {
        return evaluationResultsRepo.findAllByOrderByTotalScoreDesc();
    }
}
