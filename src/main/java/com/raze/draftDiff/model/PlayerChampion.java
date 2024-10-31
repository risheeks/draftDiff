package com.raze.draftDiff.model;

public class PlayerChampion {
    Player player;
    Champion champion;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    @Override
    public String toString() {
        return "PlayerChampion{" +
                "player=" + player +
                ", champion=" + champion +
                '}';
    }
}
