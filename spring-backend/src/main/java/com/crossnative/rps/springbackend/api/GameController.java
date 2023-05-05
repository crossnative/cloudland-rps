package com.crossnative.rps.springbackend.api;

import com.crossnative.rps.springbackend.model.Player;
import com.crossnative.rps.springbackend.model.RockPaperScissorsGame;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/games")
public class GameController {

  private final Map<UUID, RockPaperScissorsGame> rpsGames = new HashMap<>();

  @PostMapping
  public RockPaperScissorsGame createGame() {
    final var newGame = new RockPaperScissorsGame();
    this.rpsGames.put(newGame.getId(), newGame);

    return newGame;
  }

  @PostMapping("/{id}/join")
  public RockPaperScissorsGame joinGame(@PathVariable final UUID id,
      @RequestBody final Player player) {
    return this.rpsGames.get(id).joinGame(player);
  }

  @PostMapping("/{id}/choose/{playerId}/{choice}")
  public RockPaperScissorsGame choose(@PathVariable final UUID id,
      @PathVariable final UUID playerId,
      @PathVariable final String choice) {
    return this.rpsGames.get(id).updateChoice(playerId, choice);
  }

  @GetMapping("/{id}")
  public RockPaperScissorsGame getGame(@PathVariable final UUID id) {
    return this.rpsGames.get(id);
  }

}
