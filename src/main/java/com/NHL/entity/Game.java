package com.NHL.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate gameDate;
    private String location;

    // Many-to-Many (owning side)
    // This creates and controls the join table team_games
    @JsonIgnoreProperties({"players", "games"}) // avoids recursion and huge payloads
    @ManyToMany
    @JoinTable(
            name = "team_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    public Game() {}

    public Game(LocalDate gameDate, String location) {
        this.gameDate = gameDate;
        this.location = location;
    }

    // ---- Getters/Setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getGameDate() { return gameDate; }
    public void setGameDate(LocalDate gameDate) { this.gameDate = gameDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Set<Team> getTeams() { return teams; }
    public void setTeams(Set<Team> teams) { this.teams = teams; }
}
