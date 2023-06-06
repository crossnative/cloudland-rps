package com.crossnative.rps.springbackend.service;

import com.crossnative.rps.springbackend.model.Game;
import com.crossnative.rps.springbackend.model.GameResult;
import com.crossnative.rps.springbackend.model.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class RpsGameService implements GameService {

  private static final Random RANDOM = new Random();

  private final Map<UUID, Game> rpsGames = new HashMap<>();

  @AllArgsConstructor
  private enum Choice {
    ROCK, PAPER, SCISSORS;

    public static int fromPlayer(final Player player) {
      return Choice.valueOf(player.getChoice().toUpperCase()).ordinal();
    }
  }

  @Override
  public final Game getGame(final UUID id) {
    return this.rpsGames.get(id);
  }

  @Override
  public Game createGame() {
    final var game = new Game(RpsGameService::evaluate);
    this.rpsGames.put(game.getId(), game);
    return game;
  }

  @Override
  public Game joinGame(final UUID gameId, final Player player) {
    final var game = this.rpsGames.get(gameId);
    game.addPlayer(player);
    return game;
  }

  @Override
  public void pickRandomChoice(Player player) {
    var choices = Choice.values();
    player.setChoice(choices[RANDOM.nextInt(choices.length)].toString());
  }

  @Override
  public Game registerPlayerChoice(final UUID gameId, final UUID playerId,
      final String choice) {
    final var game = this.rpsGames.get(gameId);
    final var choosingPlayer = game.getPlayer(playerId);
    choosingPlayer.setChoice(Choice.valueOf(choice.toUpperCase()).name());
    return game;
  }

  private static GameResult evaluate(final Player player1, final Player player2) {
    final var result = Math.floorMod(Choice.fromPlayer(player1)
        - Choice.fromPlayer(player2), 3);

    return switch (result) {
      case 0 -> GameResult.tie();
      case 1 -> GameResult.playerWins(player1, player2);
      case 2 -> GameResult.playerWins(player2, player1);
      default -> null;
    };

  }

}
