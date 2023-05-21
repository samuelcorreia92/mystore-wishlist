package com.myrpg.demo.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        name = "GameRestController",
        path = "/api/v1/game",
        consumes = "application/json",
        produces = "application/json"
)
public class GameRestController {

    @RequestMapping(
            name = "getGameList",
            path = "/list"
    )
    public ResponseEntity<List<String>> getGameList() {
        return ResponseEntity.ok(List.of("Game 1", "Game 2", "Game 3"));
    }

}
