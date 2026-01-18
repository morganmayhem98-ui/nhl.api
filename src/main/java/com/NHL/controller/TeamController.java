package com.NHL.controller;

import com.NHL.entity.Team;
import com.NHL.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

  private final TeamService service;

  public TeamController(TeamService service) {
    this.service = service;
  }

  @PostMapping
  public Team create(@RequestBody Team team) {
    return service.create(team);
  }

  @GetMapping
  public List<Team> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public Team getById(@PathVariable Long id) {
    return service.getById(id);
  }

  @PutMapping("/{id}")
  public Team update(@PathVariable Long id, @RequestBody Team team) {
    return service.update(id, team);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
