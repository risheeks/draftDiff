package com.raze.draftDiff.model;

import com.raze.draftDiff.model.key.PlayerChampionRoleKey;
import jakarta.persistence.*;

@Entity
public class Points {

    @EmbeddedId
    PlayerChampionRoleKey id;
    Integer value;

    public PlayerChampionRoleKey getId() {
        return id;
    }

    public void setId(PlayerChampionRoleKey id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Points{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
