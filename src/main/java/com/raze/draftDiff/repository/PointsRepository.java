package com.raze.draftDiff.repository;

import com.raze.draftDiff.model.Points;
import com.raze.draftDiff.model.key.PlayerChampionRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, PlayerChampionRoleKey> {
}
