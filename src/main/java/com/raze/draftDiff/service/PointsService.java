package com.raze.draftDiff.service;

import com.raze.draftDiff.model.Champion;
import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.model.PlayerChampion;
import com.raze.draftDiff.model.Points;
import com.raze.draftDiff.model.key.PlayerChampionRoleKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointsService {

    public List<PlayerChampion> getPlayerChampionPairsForPlayer(Player player) {
        List<PlayerChampion> pairs = new ArrayList<>();
        for(Champion champion: player.getChampions()) {
            PlayerChampion pair = new PlayerChampion();
            pair.setChampion(champion);
            pair.setPlayer(player);
            pairs.add(pair);
        }
        return pairs;
    }
}
