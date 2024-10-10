package com.example.demo.service;

import com.example.demo.model.Criteria;
import com.example.demo.model.EvaluationCriterion;
import com.example.demo.repository.CriteriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepo criteriaRepo;

    public Criteria saveCriteria(Criteria criteria) {
        // Validate the weights
        if (!validateWeights(criteria)) {
            throw new IllegalArgumentException("The total weight exceeds 100");
        }
        return criteriaRepo.save(criteria);
    }

    // Validate that attendance_weight + sum of weights in EvaluationCriterion does not exceed 100
    private boolean validateWeights(Criteria criteria) {
        int totalWeight = criteria.getAttendance_weight();

        // Loop through evaluationCriteria to sum the weight
        for (EvaluationCriterion criterion : criteria.getEvaluationCriteria()) {
            totalWeight += criterion.getWeight();
        }

        // Check if total weight exceeds 100
        return totalWeight <= 100;
    }

    // เมธอดเพื่อดึง Criteria ตาม ID (ฟิลด์ @Id)
    public Criteria getCriteriaById(String id) {
        return criteriaRepo.findById(Integer.valueOf(id)).orElse(null);
    }

    // เมธอดเพื่อดึง Criteria ตาม criteriaId (ฟิลด์ criteriaId)
    public Criteria getCriteriaByCriteriaId(String criteriaId) {
        return criteriaRepo.findByCriteriaId(criteriaId);
    }

    // สามารถเพิ่มเมธอดอื่นๆ ตามความต้องการ
}
