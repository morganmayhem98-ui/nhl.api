package com.NHL.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;

    // One-to-Many: Team -> Players
    // If your Player has: @ManyToOne private Team team;
    @JsonIgnore // keeps Swagger responses clean and avoids recursion
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    // Many-to-Many (inverse side)
    @JsonIgnore // avoids recursion
    @ManyToMany(mappedBy = "teams")
    private Set<Game> games = new HashSet<>();

    public Team() {}

    public Team(String name, String city) {
        this.name = name;
        this.city = city;
    }

    // ---- Getters/Setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public Set<Game> getGames() { return games; }
    public void setGames(Set<Game> games) { this.games = games; }
}
