package com.raze.draftDiff.model;

import java.util.List;

public class PlayerChampion {
    Player player;
    Champion champion;
    List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "PlayerChampion{" +
                "player=" + player +
                ", champion=" + champion +
                ", roles=" + roles +
                '}';
    }
}
