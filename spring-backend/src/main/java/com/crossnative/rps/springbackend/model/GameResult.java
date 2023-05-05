package com.crossnative.rps.springbackend.model;

import com.crossnative.rps.springbackend.model.RockPaperScissorsGame.Choice;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GameResult {

  private UUID winnerId;

  private String message;

  public static GameResult tie() {
    return new GameResult(null, "Tie!");
  }

  public static GameResult playerWins(final Player winner, final Player looser) {
    return new GameResult(winner.getId(),
        winner.getName() + " wins! - "
            + Choice.values()[winner.getChoice()]
            + " beats "
            + Choice.values()[looser.getChoice()]);
  }
}
