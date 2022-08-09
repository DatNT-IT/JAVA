package com.example.duanlon.service;

import com.example.duanlon.core.Rank;
import com.example.duanlon.model.Detective;

import java.util.Optional;
import java.util.Set;

public interface IDetectiveService extends IAbstractService<Detective>{
    Detective createDetective(Detective detective);
    Optional<Detective> findByBadgeNumber(String badgeNumber);
    Set<Detective> findByRank(Rank rank);
}
