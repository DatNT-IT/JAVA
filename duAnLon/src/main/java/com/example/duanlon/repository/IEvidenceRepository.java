package com.example.duanlon.repository;

import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IEvidenceRepository extends JpaRepository<Evidence,Long> {
    Set<Evidence> findByCriminalCase(CriminalCase criminalCase);
    Optional<Evidence> findByNumber(String evidenceNumber);
    List<Evidence> findByCriminalCase_Number(String number);
}
