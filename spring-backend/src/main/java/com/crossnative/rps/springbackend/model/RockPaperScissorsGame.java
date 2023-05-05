package com.crossnative.rps.springbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RockPaperScissorsGame {

  @AllArgsConstructor
  public enum Choice {
    ROCK, PAPER, SCISSORS;
  }

  public enum GameState {
    WAITING_FOR_PLAYERS,
    WAITING_FOR_CHOICES,
    DONE
  }

  private UUID id = UUID.randomUUID();

  @JsonIgnore
  private List<Player> players = new ArrayList<>();

  @JsonProperty(value = "player1")
  public Player getPlayerOne() {
    return this.players.size() > 0
        ? this.players.get(0)
        : null;
  }

  @JsonProperty(value = "player2")
  public Player getPlayerTwo() {
    return this.players.size() > 1
        ? this.players.get(1)
        : null;
  }

  @JsonProperty
  public GameState getGameState() {
    if (this.players.size() < 2) {
      return GameState.WAITING_FOR_PLAYERS;
    } else if (this.players.get(0).hasChosen() && this.players.get(1).hasChosen()) {
      return GameState.DONE;
    } else {
      return GameState.WAITING_FOR_CHOICES;
    }
  }
  
  @JsonInclude(Include.NON_NULL)
  public GameResult getResult() {
    if (this.getGameState() != GameState.DONE) {
      return null;
    }

    final var result =
        Math.floorMod(this.players.get(0).getChoice() - this.players.get(1).getChoice(), 3);

    return switch (result) {
      case 0 -> GameResult.tie();
      case 1 -> GameResult.playerWins(this.players.get(0), this.players.get(1));
      case 2 -> GameResult.playerWins(this.players.get(1), this.players.get(0));
      default -> null;
    };
  }

  public RockPaperScissorsGame joinGame(final Player player) {
    if (this.players.size() >= 2) {
      throw new IllegalArgumentException("Game is full already");
    }

    this.players.add(player);
    return this;
  }

  public RockPaperScissorsGame updateChoice(final UUID playerid, final String choice) {
    this.players.stream()
        .filter(player -> player.getId().equals(playerid))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Player is not part of this game!"))
        .setChoice(Choice.valueOf(choice.toUpperCase()).ordinal());

    return this;
  }
}
