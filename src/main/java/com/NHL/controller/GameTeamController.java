package com.NHL.controller;

import com.NHL.entity.Game;
import com.NHL.entity.Team;
import com.NHL.repository.GameRepository;
import com.NHL.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameTeamController {

    private final GameRepository gameRepo;
    private final TeamRepository teamRepo;

    public GameTeamController(GameRepository gameRepo, TeamRepository teamRepo) {
        this.gameRepo = gameRepo;
        this.teamRepo = teamRepo;
    }

    // ✅ ADD team to game
    @PostMapping("/{gameId}/teams/{teamId}")
    public Game addTeamToGame(@PathVariable Long gameId, @PathVariable Long teamId) {

        Game game = gameRepo.findById(gameId).orElseThrow();
        Team team = teamRepo.findById(teamId).orElseThrow();

        game.getTeams().add(team);
        return gameRepo.save(game);
    }

    // ✅ REMOVE team from game
    @DeleteMapping("/{gameId}/teams/{teamId}")
    public Game removeTeamFromGame(@PathVariable Long gameId, @PathVariable Long teamId) {

        Game game = gameRepo.findById(gameId).orElseThrow();
        Team team = teamRepo.findById(teamId).orElseThrow();

        game.getTeams().remove(team);
        return gameRepo.save(game);
    }
}
