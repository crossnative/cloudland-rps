package com.crossnative.rps.springbackend.service;

import com.crossnative.rps.springbackend.model.Game;
import com.crossnative.rps.springbackend.model.Player;
import java.util.UUID;

public interface GameService {

  Game getGame(UUID id);

  Game createGame();

  Game joinGame(UUID gameId, Player player);

  Game registerPlayerChoice(UUID gameId, UUID playerId, String choice);
}
