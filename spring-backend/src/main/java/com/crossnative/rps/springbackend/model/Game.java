package com.crossnative.rps.springbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class Game {

  @JsonIgnore
  private final BiFunction<Player, Player, GameResult> evaluateResult;
  private UUID id = UUID.randomUUID();

  @JsonIgnore
  private List<Player> players = new ArrayList<>();

  public Game(final BiFunction<Player, Player, GameResult> evaluateResult) {
    this.evaluateResult = evaluateResult;
  }

  @JsonProperty(value = "player1")
  public Player getPlayerOne() {
    return !this.players.isEmpty()
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

  @JsonProperty(value = "result")
  public GameResult getGameResult() {
    if (this.getGameState().equals(GameState.DONE)) {
      return this.evaluateResult.apply(this.players.get(0), this.players.get(1));
    } else {
      return null;
    }
  }

  public void addPlayer(final Player player) {
    if (this.players.stream().anyMatch(p -> p.getId().equals(player.getId()))) {
      throw new IllegalArgumentException("Player is already part of this game!");
    }
    if (Objects.isNull(player.getId()) || Objects.isNull(player.getName())) {
      throw new IllegalArgumentException("Player needs to provide id and name in order to join!");
    }
    if (this.players.size() >= 2) {
      throw new IllegalArgumentException("Game is full already");
    }

    this.players.add(player);
  }

  public Player getPlayer(final UUID playerId) {
    return this.players
        .stream()
        .filter(p -> playerId.equals(p.getId()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Player is not part of this game!"));
  }

  public enum GameState {
    WAITING_FOR_PLAYERS,
    WAITING_FOR_CHOICES,
    DONE
  }
}
