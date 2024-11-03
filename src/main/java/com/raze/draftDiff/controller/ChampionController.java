package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Champion;
import com.raze.draftDiff.service.ChampionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/champion")
public class ChampionController {

    @Autowired
    ChampionService championService;

    @GetMapping("/")
    public List<Champion> findAll() {
        return championService.findAll();
    }
    @GetMapping("/{championId}")
    public Champion findById(@PathVariable("championId") String id) {
        return championService.findById(id);
    }

}
