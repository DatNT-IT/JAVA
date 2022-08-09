package com.example.duanlon.repository;

import com.example.duanlon.core.CaseStatus;
import com.example.duanlon.core.CaseType;
import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.model.Detective;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class ICriminalCaseRepositoryTest {
    @Autowired
    private ICriminalCaseRepository criminalCaseRepository;

    @Test
    void findById() {
        CriminalCase criminalCase = getCriminalCase();
        criminalCaseRepository.save(criminalCase);
        log.info("findById CriminalCase:{}", criminalCaseRepository.findById(criminalCase.getId()));
    }

    @Test
    void save() {
        CriminalCase criminalCase = getCriminalCase();
        criminalCaseRepository.save(criminalCase);
        log.info("save CriminalCase:{}", criminalCaseRepository.findById(criminalCase.getId()));
    }

    @Test
    void deleteById() {
        CriminalCase criminalCase = getCriminalCase();
        criminalCaseRepository.save(criminalCase);
        criminalCaseRepository.deleteById(criminalCase.getId());
        List<CriminalCase> result = new ArrayList<>(criminalCaseRepository.findAll());
        assertEquals(1, result.size());
    }

    @Test
    void findAll() {
        CriminalCase criminalCase = getCriminalCase();
        criminalCaseRepository.save(criminalCase);
        List<CriminalCase> result = new ArrayList<>(this.criminalCaseRepository.findAll());
        assertEquals(1, result.size());
    }


    private CriminalCase getCriminalCase() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCreatedAt(LocalDateTime.now());
        criminalCase.setModifiedAt(LocalDateTime.now());
        criminalCase.setVersion(2);
        criminalCase.setDetailedDescription("crim1");
        criminalCase.setNotes("abc");
        criminalCase.setNumber("231124");
        criminalCase.setShortDescription("02568432");
        criminalCase.setStatus(CaseStatus.valueOf("SUB"));
        criminalCase.setType(CaseType.valueOf("TYPE"));
        Detective person = new Detective();
        person.setId(3L);
        criminalCase.setLeadInvestigator(person);
        return criminalCase;
    }
}