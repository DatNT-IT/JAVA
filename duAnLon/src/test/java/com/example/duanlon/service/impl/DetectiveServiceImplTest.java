package com.example.duanlon.service.impl;

import com.example.duanlon.core.EmploymentStatus;
import com.example.duanlon.core.Rank;
import com.example.duanlon.model.Detective;
import com.example.duanlon.model.Person;
import com.example.duanlon.service.IDetectiveService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class DetectiveServiceImplTest {
    @Autowired
    private IDetectiveService detectiveService;

    @Test
    void save() {
        Detective detective = new Detective();
        detective.setArmed(true);
        detective.setBadgeNumber("dsad");
        detective.setRank(Rank.INSPECTOR);
        detective.setStatus(EmploymentStatus.ACTIVE);
        detective.setPerson(new Person());
        detectiveService.save(detective);
        log.info("save detective : {}",detective);
    }

    @Test
    void findById() {
        log.info("find detective : {}",detectiveService.findById(1L));
    }

    @Test
    void delete() {
        Detective detective = detectiveService.findById(1L);
        detectiveService.delete(detective);
        log.info("delete detective : {}",detective);
    }

    @Test
    void deleteById() {
        detectiveService.deleteById(1L);
    }

    @Test
    void findAll() {
        log.info("delete detective : {}",detectiveService.findAll());
    }

    @Test
    void createDetective() {
        Detective detec = detectiveService.findById(1L);
        Detective detective = new Detective();
        detective.setPerson(detec.getPerson());
        detective.setRank(detec.getRank());
        detective.setBadgeNumber(detec.getBadgeNumber());
        detective.setArmed(detec.getArmed());
        detective.setStatus(detec.getStatus());
        detective.setCriminalCases(detec.getCriminalCases());
        detective.setTrackEntries(detec.getTrackEntries());
        detectiveService.createDetective(detective);
        assertEquals(1,detective.getId());

    }

    @Test
    void findByBadgeNumber() {
        Detective detec = detectiveService.findByBadgeNumber("entity").get();
        log.info("id detective : {}",detec.getId());
        assertEquals(1,detec.getId());
    }

    @Test
    void findByRank() {
        Set<Detective> detectiveSet = detectiveService.findByRank(Rank.INSPECTOR);
        log.info("findByRank detective : {}",detectiveSet);
    }
}