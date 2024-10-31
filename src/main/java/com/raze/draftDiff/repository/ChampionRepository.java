package com.raze.draftDiff.repository;

import com.raze.draftDiff.model.Champion;
import com.raze.draftDiff.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, String> {

}
