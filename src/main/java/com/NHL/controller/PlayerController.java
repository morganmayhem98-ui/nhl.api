package com.NHL.controller;

import com.NHL.entity.Player;
import com.NHL.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository playerRepo;

    public PlayerController(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    // CREATE
    @PostMapping
    public Player create(@RequestBody Player player) {
        return playerRepo.save(player);
    }

    // READ ALL
    @GetMapping
    public List<Player> getAll() {
        return playerRepo.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Player getById(@PathVariable Long id) {
        return playerRepo.findById(id).orElseThrow();
    }

    // DELETE (optional but nice)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerRepo.deleteById(id);
    }
}
