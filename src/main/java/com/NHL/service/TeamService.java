package com.NHL.service;

import com.NHL.entity.Team;
import com.NHL.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeamService {

  private final TeamRepository repo;

  public TeamService(TeamRepository repo) {
    this.repo = repo;
  }

  public Team create(Team team) {
    return repo.save(team);
  }

  public List<Team> getAll() {
    return repo.findAll();
  }

  public Team getById(Long id) {
    return repo.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found: " + id));
  }

  public Team update(Long id, Team updated) {
    Team existing = getById(id);
    existing.setName(updated.getName());
    existing.setCity(updated.getCity());
    return repo.save(existing);
  }

  public void delete(Long id) {
    if (!repo.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found: " + id);
    }
    repo.deleteById(id);
  }
}
