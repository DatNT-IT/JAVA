package com.example.duanlon.repository;

import com.example.duanlon.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStorageRepository extends JpaRepository<Storage,Long> {
Optional<Storage> findByName(String name);
Optional<Storage> findByLocation(String location);
}
