package com.raze.draftDiff.service;

import com.raze.draftDiff.model.*;
import com.raze.draftDiff.repository.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointsService {

    @Autowired
    private PointsRepository pointsRepository;

    @Autowired
    private RoleService roleService;

    public Points save(Points points) {
        return pointsRepository.save(points);
    }

    public List<PlayerChampion> getPlayerChampionPairsForPlayer(Player player) {
        List<PlayerChampion> pairs = new ArrayList<>();
        List<Role> roles = roleService.findAll();
        for (Champion champion : player.getChampions()) {
            PlayerChampion pair = new PlayerChampion();
            pair.setChampion(champion);
            pair.setPlayer(player);
            pair.setRoles(roles);
            pairs.add(pair);
        }
        return pairs;
    }
}
