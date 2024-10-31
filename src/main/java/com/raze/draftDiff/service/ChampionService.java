package com.raze.draftDiff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raze.draftDiff.model.Champion;
import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.model.riot.RiotAccount;
import com.raze.draftDiff.model.riot.RiotChampion;
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

    public List<Champion> getChampionsForPlayer(Player player) {
        RestTemplate restTemplate = new RestTemplate();
        String[] nameSplit = player.getIgn().split("#");
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
        try {
            url = new URL("https://euw1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/" + riotAccount.getPuuid() + "?api_key=" + apiKey);
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
            if(riotChampion.getChampionPoints()>=minimumPoints)
                champions.add(getChampionInfo(championsMap, riotChampion));
        }
        System.out.println(champions.size());
        return champions;
    }

    private Champion getChampionInfo(LinkedHashMap<String, Object> championsMap, RiotChampion riotChampion) {
        Set<String> championNames = championsMap.keySet();
        for(String name: championNames) {
            LinkedHashMap championInfo = ((LinkedHashMap)championsMap.get(name));
            if(championInfo.get("key").toString().equals(riotChampion.getChampionId().toString())) {
                Champion champion = new Champion();
                champion.setId(riotChampion.getChampionId().toString());
                champion.setImg(championInfo.get("image").toString());
                champion.setName(championInfo.get("name").toString());
                return champion;
            }
        }
        try {
            throw new Exception("Champion not found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
