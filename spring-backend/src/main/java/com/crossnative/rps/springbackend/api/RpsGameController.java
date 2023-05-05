package com.crossnative.rps.springbackend.api;

import com.crossnative.rps.springbackend.model.Game;
import com.crossnative.rps.springbackend.model.Player;
import com.crossnative.rps.springbackend.service.RpsGameService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/games")
public class RpsGameController {

  private RpsGameService gameService;

  @PostMapping
  public Game createGame() {
    return this.gameService.createGame();
  }

  @PostMapping("/{gameId}/players")
  public Game joinGame(@PathVariable final UUID gameId,
      @RequestBody final Player player) {
    return this.gameService.joinGame(gameId, player);
  }

  @PatchMapping("{gameId}/players/{playerId}")
  public Game choose(@PathVariable final UUID gameId,
      @PathVariable final UUID playerId, @RequestBody final Player playerUpdate) {
    return this.gameService.registerPlayerChoice(gameId, playerId, playerUpdate.getChoice());
  }

  @GetMapping("/{id}")
  public final Game getGame(@PathVariable final UUID id) {
    return this.gameService.getGame(id);
  }

}
