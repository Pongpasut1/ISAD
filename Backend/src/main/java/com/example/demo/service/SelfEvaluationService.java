package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.SelfEvaluation;
import com.example.demo.repository.SelfEvaluationRepository;

@Service
public class SelfEvaluationService {

    @Autowired
    private SelfEvaluationRepository selfEvaluationRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public SelfEvaluation saveSelfEvaluation(SelfEvaluation selfEvaluation) {
        selfEvaluation.setId(sequenceGeneratorService.generateSequence(SelfEvaluation.SEQUENCE_NAME));
        return selfEvaluationRepository.save(selfEvaluation);
    }
}
