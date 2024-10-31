package com.raze.draftDiff.repository;

import com.raze.draftDiff.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player>  findByIgn(String ign);
}
