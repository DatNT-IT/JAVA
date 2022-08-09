package com.example.duanlon.service.impl;

import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.model.Evidence;
import com.example.duanlon.repository.IEvidenceRepository;
import com.example.duanlon.service.IEvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class EvidenceServiceImpl implements IEvidenceService {
    private IEvidenceRepository iEvidenceRepository;

    @Autowired
    public EvidenceServiceImpl(IEvidenceRepository iEvidenceRepository) {
        this.iEvidenceRepository = iEvidenceRepository;
    }

    @Override
    public Evidence save(Evidence entity) {
        iEvidenceRepository.save(entity);
        log.info("Save Evidence : {}", entity);

        return entity;
    }

    @Override
    public Evidence findById(Long entityId) {
        return iEvidenceRepository.findById(entityId).get();
    }

    @Override
    public void delete(Evidence entity) {
        iEvidenceRepository.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        boolean exist = iEvidenceRepository.existsById(entityId);
        if (exist) {
            iEvidenceRepository.deleteById(entityId);
        }
    }

    @Override
    public Iterable<Evidence> findAll() {
        return iEvidenceRepository.findAll();
    }

    @Override
    public Evidence createEvidence(Evidence evidence1) {
        Evidence evidence = new Evidence();
        evidence.setNumber(evidence.getNumber());
        evidence.setNotes(evidence1.getNotes());
        evidence.setArchived(evidence1.getArchived());
        evidence.setCriminalCase(evidence1.getCriminalCase());
        evidence.setStorage(evidence1.getStorage());
        evidence.setItemName(evidence.getItemName());
        evidence.setTrackEntries(evidence1.getTrackEntries());
        return evidence;
    }

    @Override
    public Set<Evidence> findByCriminalCase(CriminalCase criminalCase) {
        return iEvidenceRepository.findByCriminalCase(criminalCase);
    }

    @Override
    public Optional<Evidence> findByNumber(String evidenceNumber) {
        return iEvidenceRepository.findByNumber(evidenceNumber);
    }

    @Override
    public List<Evidence> findByCriminalCase_Number(String number) {
        return iEvidenceRepository.findByCriminalCase_Number(number);
    }
}
