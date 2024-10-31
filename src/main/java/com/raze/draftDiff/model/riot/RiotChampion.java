package com.raze.draftDiff.model.riot;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RiotChampion {
    @JsonProperty("championId")
    Integer championId;
    @JsonProperty("championLevel")
    Integer championLevel;
    @JsonProperty("championPoints")
    Long championPoints;

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Integer getChampionLevel() {
        return championLevel;
    }

    public void setChampionLevel(Integer championLevel) {
        this.championLevel = championLevel;
    }

    public Long getChampionPoints() {
        return championPoints;
    }

    public void setChampionPoints(Long championPoints) {
        this.championPoints = championPoints;
    }

    @Override
    public String toString() {
        return "RiotChampion{" +
                "championId=" + championId +
                ", championLevel=" + championLevel +
                ", championPoints=" + championPoints +
                '}';
    }
}
