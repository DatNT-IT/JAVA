package com.example.duanlon.repository;

import com.example.duanlon.core.CaseStatus;
import com.example.duanlon.model.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICriminalCaseRepository extends JpaRepository<CriminalCase, Long> {
    List<CriminalCase> findByStatus(CaseStatus status);
    List<CriminalCase> findByAssigned_Username (String username);
}
