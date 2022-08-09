package com.example.duanlon.repository;

import com.example.duanlon.model.TrackEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrackEntryRepository extends JpaRepository<TrackEntry,Long> {
}
