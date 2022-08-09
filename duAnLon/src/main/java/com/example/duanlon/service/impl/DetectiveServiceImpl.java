package com.example.duanlon.service.impl;

import com.example.duanlon.core.Rank;
import com.example.duanlon.model.*;
import com.example.duanlon.repository.IDetectiveRepository;
import com.example.duanlon.service.IDetectiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class DetectiveServiceImpl implements IDetectiveService {
    private IDetectiveRepository iDetectiveRepository;

    @Autowired
    public DetectiveServiceImpl(IDetectiveRepository iDetectiveRepository) {
        this.iDetectiveRepository = iDetectiveRepository;
    }

    @Override
    public Detective save(Detective entity) {
        iDetectiveRepository.save(entity);
        log.info("Save Detective : {}", entity);
        return entity;
    }

    @Override
    public Detective findById(Long entityId) {
        return iDetectiveRepository.findById(entityId).get();
    }

    @Override
    public void delete(Detective entity) {
iDetectiveRepository.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        boolean exist = iDetectiveRepository.existsById(entityId);
        if (exist) {
            iDetectiveRepository.deleteById(entityId);
        }
    }

    @Override
    public Iterable<Detective> findAll() {
        return iDetectiveRepository.findAll();
    }

    @Override
    public Detective createDetective(Detective detec) {
        Detective detective = new Detective();
        detective.setPerson(detec.getPerson());
        detective.setRank(detec.getRank());
        detective.setBadgeNumber(detec.getBadgeNumber());
        detective.setArmed(detec.getArmed());
        detective.setStatus(detec.getStatus());
        detective.setCriminalCases(detec.getCriminalCases());
        detective.setTrackEntries(detec.getTrackEntries());
        iDetectiveRepository.save(detective);
        return detective;
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return iDetectiveRepository.findByBadgeNumber(badgeNumber);
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        return findByRank(rank);
    }
}
