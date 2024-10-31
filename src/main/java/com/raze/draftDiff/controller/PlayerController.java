package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.service.ChampionService;
import com.raze.draftDiff.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ChampionService championService;

    @GetMapping("/initPlayer")
    public Player initPlayer(@RequestParam String name, @RequestParam String ign) {
        Player player = playerService.createPlayer(name, ign);
        player.setChampions(championService.getChampionsForPlayer(player));
        return player;
    }

    @GetMapping("/")
    public String blank() {
        return "Hello World!";
    }
}
