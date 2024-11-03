package com.raze.draftDiff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.model.riot.RiotAccount;
import com.raze.draftDiff.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Value("${api.key}")
    private String apiKey;
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player createPlayer(String puuid, String name, String ign) {
        Player player = new Player();
        player.setId(puuid);
        player.setName(name);
        player.setIgn(ign);
        return player;
    }

    public String getPUUIDByIgn(String ign) {
        RestTemplate restTemplate = new RestTemplate();
        String[] nameSplit = ign.split("#");
        URL url = null;
        try {
            url = new URL("https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + nameSplit[0] + "/" + nameSplit[1] + "?api_key=" + apiKey);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url.toString(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        RiotAccount riotAccount = null;
        try {
            riotAccount = mapper.readValue(responseEntity.getBody(), RiotAccount.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return riotAccount.getPuuid();
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
