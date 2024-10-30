package com.raze.draftDiff.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PlayerChampionRoleKey {
    @Column(name = "player_id")
    String PlayerId;
    @Column(name = "champion_id")
    String ChampionId;
    @Column(name = "role_id")
    String RoleId;

    public String getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(String playerId) {
        PlayerId = playerId;
    }

    public String getChampionId() {
        return ChampionId;
    }

    public void setChampionId(String championId) {
        ChampionId = championId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    @Override
    public String toString() {
        return "PlayerChampionRoleKey{" +
                "PlayerId='" + PlayerId + '\'' +
                ", ChampionId='" + ChampionId + '\'' +
                ", RoleId='" + RoleId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PlayerChampionRoleKey that = (PlayerChampionRoleKey) object;
        return Objects.equals(getPlayerId(), that.getPlayerId()) && Objects.equals(getChampionId(), that.getChampionId()) && Objects.equals(getRoleId(), that.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getChampionId(), getRoleId());
    }
}
