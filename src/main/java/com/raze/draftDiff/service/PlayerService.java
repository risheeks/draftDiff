package com.raze.draftDiff.service;

import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(String name, String ign) {
        Player player = new Player();
        player.setId(UUID.randomUUID().toString());
        player.setName(name);
        player.setIgn(ign);
        return player;
    }

}
