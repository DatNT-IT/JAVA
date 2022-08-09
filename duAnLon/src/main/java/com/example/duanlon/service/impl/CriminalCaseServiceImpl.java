package com.example.duanlon.service.impl;

import com.example.duanlon.core.CaseStatus;
import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.repository.ICriminalCaseRepository;
import com.example.duanlon.service.ICriminalCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CriminalCaseServiceImpl implements ICriminalCaseService {
    private ICriminalCaseRepository iCriminalCaseRepository;

    @Autowired
    public CriminalCaseServiceImpl(ICriminalCaseRepository iCriminalCaseRepository) {
        this.iCriminalCaseRepository = iCriminalCaseRepository;
    }

    @Override
    public CriminalCase save(CriminalCase entity) {
        iCriminalCaseRepository.save(entity);
        log.info("Save CriminalCase : {}", entity);
        return entity;
    }

    @Override
    public CriminalCase findById(Long entityId) {
        return iCriminalCaseRepository.findById(entityId).get();
    }

    @Override
    public void delete(CriminalCase entity) {
        iCriminalCaseRepository.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        boolean exist = iCriminalCaseRepository.existsById(entityId);
        if (exist) {
            iCriminalCaseRepository.deleteById(entityId);
        }
    }

    @Override
    public List<CriminalCase> findAll() {
        return iCriminalCaseRepository.findAll();
    }

    @Override
    public CriminalCase createCriminalCase(CriminalCase crim) {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setNumber(crim.getNumber());
        criminalCase.setType(crim.getType());
        criminalCase.setDetailedDescription(crim.getDetailedDescription());
        criminalCase.setShortDescription(crim.getShortDescription());
        criminalCase.setStatus(crim.getStatus());
        criminalCase.setNotes(crim.getNotes());
        criminalCase.setEvidenceSet(crim.getEvidenceSet());
        criminalCase.setLeadInvestigator(crim.getLeadInvestigator());
        iCriminalCaseRepository.save(criminalCase);
        return criminalCase;
    }

    @Override
    public List<CriminalCase> findAllByStatus(CaseStatus status) {
        return iCriminalCaseRepository.findByStatus(status);
    }

    @Override
    public List<CriminalCase> findByAssigned_Username(String username) {
        return iCriminalCaseRepository.findByAssigned_Username(username);
    }

}
