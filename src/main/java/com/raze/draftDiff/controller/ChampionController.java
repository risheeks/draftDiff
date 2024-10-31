package com.raze.draftDiff.controller;

import com.raze.draftDiff.service.ChampionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/champion")
public class ChampionController {

    @Autowired
    ChampionService championService;

    @PostMapping("/initChampions")
    public String initChampions() {
        try {
            championService.initChampions();
            return "Champion Initialization Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Champion Initialization Failed";
        }
    }
}
