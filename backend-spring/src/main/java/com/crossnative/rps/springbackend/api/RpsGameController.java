package com.crossnative.rps.springbackend.api;

import com.crossnative.rps.springbackend.model.Game;
import com.crossnative.rps.springbackend.model.Player;
import com.crossnative.rps.springbackend.service.GameService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/games")
@Slf4j
public class RpsGameController {

  private static final String PLAYER_VS_COMPUTER = "PlayerVsComputer";

  private GameService gameService;

  @PostMapping
  public Game createGame(@RequestParam(required = false) String mode) {
    log.info("Received POST on api/v1/games");
    var game = this.gameService.createGame();

    if (PLAYER_VS_COMPUTER.equals(mode)) {
      var player = Player.getComputerPlayer();
      gameService.pickRandomChoice(player);
      gameService.joinGame(game.getId(), player);
    }
    return game;
  }

  @PostMapping("/{gameId}/players")
  public Game joinGame(@PathVariable final UUID gameId,
      @RequestBody final Player player) {
    log.info("Received POST on api/v1/games/{id}/players");
    return this.gameService.joinGame(gameId, player);
  }

  @PatchMapping("{gameId}/players/{playerId}")
  public Game choose(@PathVariable final UUID gameId,
      @PathVariable final UUID playerId, @RequestBody final Player playerUpdate) {
    log.info("Received PATCH on api/v1/games/{id}/players");
    return this.gameService.registerPlayerChoice(gameId, playerId, playerUpdate.getChoice());
  }

  @GetMapping("/{id}")
  public final Game getGame(@PathVariable final UUID id) {
    log.info("Received GET on api/v1/games/{id}");
    return this.gameService.getGame(id);
  }

}
