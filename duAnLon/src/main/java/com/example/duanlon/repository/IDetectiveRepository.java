package com.example.duanlon.repository;

import com.example.duanlon.model.Detective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDetectiveRepository extends JpaRepository<Detective,Long> {
   Optional<Detective> findByBadgeNumber(String badgeNumber);
}
