package com.raze.draftDiff.repository;

import com.raze.draftDiff.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {

}
