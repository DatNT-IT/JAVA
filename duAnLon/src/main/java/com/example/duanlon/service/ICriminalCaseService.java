package com.example.duanlon.service;

import com.example.duanlon.core.CaseStatus;
import com.example.duanlon.model.CriminalCase;

import java.util.List;

public interface ICriminalCaseService extends IAbstractService<CriminalCase> {
    CriminalCase createCriminalCase(CriminalCase criminalCase);
    List<CriminalCase> findAllByStatus(CaseStatus status);
    List<CriminalCase> findByAssigned_Username (String username);
}
