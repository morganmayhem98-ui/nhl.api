package com.NHL.controller;

import com.NHL.entity.Game;
import com.NHL.repository.GameRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepo;

    public GameController(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    // CREATE
    @PostMapping
    public Game create(@RequestBody Game game) {
        return gameRepo.save(game);
    }

    // READ ALL
    @GetMapping
    public List<Game> getAll() {
        return gameRepo.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Game getById(@PathVariable Long id) {
        return gameRepo.findById(id).orElseThrow();
    }

    // DELETE (optional but helpful)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gameRepo.deleteById(id);
    }
}
