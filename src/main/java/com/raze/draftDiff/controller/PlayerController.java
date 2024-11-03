package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Champion;
import com.raze.draftDiff.model.Player;
import com.raze.draftDiff.model.Points;
import com.raze.draftDiff.model.key.PlayerChampionRoleKey;
import com.raze.draftDiff.service.ChampionService;
import com.raze.draftDiff.service.PlayerService;
import com.raze.draftDiff.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ChampionService championService;

    @Autowired
    private PointsService pointsService;

    @GetMapping("/")
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/{playerId}")
    public Player findById(@PathVariable("playerId") String id) {
        return playerService.findById(id).orElse(null);
    }

    @GetMapping("/initPlayer")
    public Player initPlayer(@RequestParam String name, @RequestParam String ign) {
        String puuid = playerService.getPUUIDByIgn(ign);
        Player player = playerService.createPlayer(puuid, name, puuid);
        player.setChampions(championService.getChampionsForPlayer(player));

        return playerService.save(player);
    }

    @GetMapping("/{playerId}/champion")
    public List<Champion> championsForPlayer(@PathVariable("playerId") String playerId) {
        Optional<Player> optionalPlayer = playerService.findById(playerId);
        return optionalPlayer.map(Player::getChampions).orElse(null);
    }

    @PostMapping("/{playerId}/champions")
    public List<Points> assignPointsForPlayer(@PathVariable("playerId") String playerId, @RequestBody ChampionDataList championDataList) {
        System.out.println(championDataList);
        List<Points> pointsList = new ArrayList<>();
        for(ChampionDataList.ChampionData championData: championDataList.championData) {
            String championId = championData.getChampionId();
            String img = championData.getImg();
            for(ChampionDataList.ChampionData.Data data: championData.getData()) {
                Points points = new Points();
                PlayerChampionRoleKey key = new PlayerChampionRoleKey();
                key.setPlayerId(playerId);
                key.setChampionId(championId);
                key.setRoleId(data.getRoleId());
                points.setId(key);
                points.setImg(img);
                points.setValue(data.getPoints());
                pointsList.add(pointsService.save(points));
            }
        }
        return pointsList;
    }

    @PostMapping("/champion/{playerId}")
    public List<Points> assignPointsForPlayer(@PathVariable("playerId") String playerId, @RequestBody ChampionDataList.ChampionData championData) {
        List<Points> pointsList = new ArrayList<>();
        String championId = championData.getChampionId();
        String img = championData.getImg();
        for(ChampionDataList.ChampionData.Data data: championData.getData()) {
            Points points = new Points();
            PlayerChampionRoleKey key = new PlayerChampionRoleKey();
            key.setPlayerId(playerId);
            key.setChampionId(championId);
            key.setRoleId(data.getRoleId());
            points.setId(key);
            points.setImg(img);
            points.setValue(data.getPoints());
            pointsList.add(pointsService.save(points));
        }
        return pointsList;
    }

    @GetMapping("/playerByIgn")
    public Player findPlayerByIgn(@RequestParam String ign) {
        return playerService.findByIgn(ign);
    }


    public static class ChampionDataList {
        List<ChampionData> championData;

        public List<ChampionData> getChampionData() {
            return championData;
        }

        public void setChampionData(List<ChampionData> championData) {
            this.championData = championData;
        }

        @Override
        public String toString() {
            return "ChampionDataList{" +
                    "championData=" + championData +
                    '}';
        }

        public static class ChampionData {
            String championId;
            String img;
            List<Data> data;

            public String getChampionId() {
                return championId;
            }

            public void setChampionId(String championId) {
                this.championId = championId;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public List<Data> getData() {
                return data;
            }

            public void setData(List<Data> data) {
                this.data = data;
            }

            @Override
            public String toString() {
                return "ChampionData{" +
                        "championId='" + championId + '\'' +
                        ", img='" + img + '\'' +
                        ", data=" + data +
                        '}';
            }

            public static class Data {
                String RoleId;
                Integer points;

                public String getRoleId() {
                    return RoleId;
                }

                public void setRoleId(String roleId) {
                    RoleId = roleId;
                }

                public Integer getPoints() {
                    return points;
                }

                public void setPoints(Integer points) {
                    this.points = points;
                }

                @Override
                public String toString() {
                    return "Data{" +
                            "RoleId='" + RoleId + '\'' +
                            ", points=" + points +
                            '}';
                }
            }
        }
    }
}
