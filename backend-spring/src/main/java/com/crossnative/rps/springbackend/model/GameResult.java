package com.crossnative.rps.springbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GameResult {

  @JsonProperty(access = Access.WRITE_ONLY)
  private UUID winnerId;

  private String winner;

  private String message;

  public static GameResult tie() {
    return new GameResult(null, null, "Tie!");
  }

  public static GameResult playerWins(final Player winner, final Player looser) {
    return new GameResult(winner.getId(),
        winner.getName(),
        winner.getChoice()
            + " beats "
            + looser.getChoice());
  }
}
