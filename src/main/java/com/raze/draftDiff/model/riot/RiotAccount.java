package com.raze.draftDiff.model.riot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RiotAccount {
    @JsonProperty("puuid")
    String puuid;
    @JsonProperty("gameName")
    String gameName;
    @JsonProperty("tagLine")
    String tagLine;

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    @Override
    public String toString() {
        return "RiotAccount{" +
                "puuid='" + puuid + '\'' +
                ", gameName='" + gameName + '\'' +
                ", tagLine='" + tagLine + '\'' +
                '}';
    }
}
