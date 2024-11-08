package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.model.PlayerChampion;
import com.raze.draftDiff.model.Points;
import com.raze.draftDiff.model.key.PlayerChampionRoleKey;
import com.raze.draftDiff.service.PlayerService;
import com.raze.draftDiff.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/points")
public class PointsController {

    @Autowired
    private PointsService pointsService;
    @Autowired
    private PlayerService playerService;
    @GetMapping("/assignPlayer")
    public List<PlayerChampion> getPlayerChampionsPairsForPlayer(@RequestParam String playerId) {
        return pointsService.getPlayerChampionPairsForPlayer(playerService.findById(playerId));
    }

    @PostMapping("/assignPlayer")
    public void createPoints(@RequestBody PointsArray pointsArray) {
        for (Points points: pointsArray.getPoints()) {
            pointsService.save(points);
        }
    }

    public class PointsArray {
        Points[] points;

        public Points[] getPoints() {
            return points;
        }

        public void setPoints(Points[] points) {
            this.points = points;
        }

        @Override
        public String toString() {
            return "PointsArray{" +
                    "points=" + Arrays.toString(points) +
                    '}';
        }
    }
}
