package com.example.demo.service;

import com.example.demo.model.Criteria;
import com.example.demo.repository.CriteriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepo criteriaRepo;

    public Criteria saveCriteria(Criteria criteria) {
        // ไม่จำเป็นต้องกำหนดค่า ID เอง
        return criteriaRepo.save(criteria);
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
