package com.raze.draftDiff.model;

import com.raze.draftDiff.model.key.PlayerChampionRoleKey;
import jakarta.persistence.*;

@Entity
public class Points {

    @EmbeddedId
    PlayerChampionRoleKey id;
    Integer value;

    String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Points{" +
                "id=" + id +
                ", value=" + value +
                ", img='" + img + '\'' +
                '}';
    }
}
