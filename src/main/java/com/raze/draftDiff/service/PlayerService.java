package com.raze.draftDiff.service;

import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player createPlayer(String name, String ign) {
        Player player = new Player();
        player.setId(UUID.randomUUID().toString());
        player.setName(name);
        player.setIgn(ign);
        return player;
    }

    public Optional<Player> findById(String id) {
        return playerRepository.findById(id);
    }

    public Player findByIgn(String ign) {
        List<Player> playerList = playerRepository.findByIgn(ign);
        if(!playerList.isEmpty()) return playerList.get(0);
        return null;
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

}
