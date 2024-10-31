package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.model.PlayerChampion;
import com.raze.draftDiff.service.PlayerService;
import com.raze.draftDiff.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointsController {

    @Autowired
    private PointsService pointsService;
    @Autowired
    private PlayerService playerService;
    @GetMapping("/assignPlayer")
    public List<PlayerChampion> getPlayerChampionsPairsForPlayer(@RequestParam String playerId) {
        return pointsService.getPlayerChampionPairsForPlayer(playerService.findById(playerId).orElseThrow());
    }
}
