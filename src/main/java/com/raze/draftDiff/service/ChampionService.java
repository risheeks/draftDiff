package com.raze.draftDiff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raze.draftDiff.model.Champion;
import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.model.riot.RiotAccount;
import com.raze.draftDiff.model.riot.RiotChampion;
import com.raze.draftDiff.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class ChampionService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${champion.minimumPoints}")
    Long minimumPoints;

    @Autowired
    ChampionRepository championRepository;

    public List<Champion> findAll() {
        return championRepository.findAll();
    }

    public Champion findById(String id) {
        return championRepository.findById(id).orElse(null);
    }
    public List<Champion> getChampionsForPlayer(Player player) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        URL url = null;
        ResponseEntity<String> responseEntity;
        try {
            url = new URL("https://euw1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/" + player.getId() + "?api_key=" + apiKey);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        responseEntity = restTemplate.getForEntity(url.toString(), String.class);
        Object[] championsStr = null;
        try {
            championsStr = mapper.readValue(responseEntity.getBody(), Object[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<RiotChampion> riotChampions = new ArrayList<>();
        for(Object championStr: championsStr) {
            LinkedHashMap map = (LinkedHashMap) championStr;
            RiotChampion riotChampion = new RiotChampion();
            riotChampion.setChampionId(Integer.parseInt(map.get("championId").toString()));
            riotChampion.setChampionLevel(Integer.parseInt(map.get("championLevel").toString()));
            riotChampion.setChampionPoints(Long.parseLong(map.get("championPoints").toString()));
            riotChampions.add(riotChampion);
        }
//        System.out.println(riotChampions);
        return getChampions(riotChampions);
    }

    private List<Champion> getChampions(List<RiotChampion> riotChampions) {
        List<Champion> champions = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Object object = null;
        try {
            object = mapper.readValue(new File(("src/main/resources/champion.json")), Object.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LinkedHashMap<String, Object> championsMap = (LinkedHashMap) (((LinkedHashMap) object).get("data"));
        for(RiotChampion riotChampion: riotChampions) {
            if(riotChampion.getChampionPoints()>=minimumPoints) {
                Champion champion = getChampionInfo(championsMap, riotChampion).orElse(null);
//                System.out.println(riotChampion);
//                System.out.println(champion);
                if(champion!=null) champions.add(champion);
            }
        }
//        System.out.println(champions);
        return champions;
    }

    private Optional<Champion> getChampionInfo(LinkedHashMap<String, Object> championsMap, RiotChampion riotChampion) {
        return championRepository.findById(riotChampion.getChampionId().toString());
    }

    public void initChampions() {
        ObjectMapper mapper = new ObjectMapper();
        Object object = null;
        try {
            object = mapper.readValue(new File(("src/main/resources/champion.json")), Object.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LinkedHashMap<String, Object> championsMap = (LinkedHashMap) (((LinkedHashMap) object).get("data"));
        Set<String> championNames = championsMap.keySet();
        for(String name: championNames) {
            LinkedHashMap championInfo = ((LinkedHashMap)championsMap.get(name));
            Champion champion = new Champion();
            champion.setId(championInfo.get("key").toString());
            champion.setImg(championInfo.get("id").toString().concat("_0.jpg"));
            champion.setName(championInfo.get("name").toString());
            championRepository.save(champion);
        }
    }
}
