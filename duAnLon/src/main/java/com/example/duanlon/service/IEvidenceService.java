package com.example.duanlon.service;

import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.model.Evidence;
import com.example.duanlon.model.Storage;
import com.example.duanlon.model.TrackEntry;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IEvidenceService  extends IAbstractService<Evidence>{
    Evidence createEvidence(Evidence evidence);
    Set<Evidence> findByCriminalCase(CriminalCase criminalCase);
    Optional<Evidence> findByNumber(String evidenceNumber);
    List<Evidence> findByCriminalCase_Number(String number);
}
