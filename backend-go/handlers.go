package main

import (
	"encoding/json"
	"net/http"

	"github.com/go-chi/chi/v5"
)

func playComputerHandler(w http.ResponseWriter, r *http.Request) {
	var game Game
	err := json.NewDecoder(r.Body).Decode(&game)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	game.PlayVsComputer()

	json.NewEncoder(w).Encode(game)
}

func createGameHandler(gameRepository GameRepository) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		game, _ := gameRepository.Create(&Game{GameState: WaitingForPlayers})
		json.NewEncoder(w).Encode(game)
	}
}

func readGameHandler(gameRepository GameRepository) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		gameID := chi.URLParam(r, "GameID")
		game, _ := gameRepository.FindByID(gameID)
		json.NewEncoder(w).Encode(game)
	}
}

func playerChooseHandler(gameRepository GameRepository) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		gameID := chi.URLParam(r, "GameID")
		playerID := chi.URLParam(r, "PlayerID")
		game, _ := gameRepository.FindByID(gameID)

		var player Player
		json.NewDecoder(r.Body).Decode(&player)

		game.Choose(playerID, player.Choice)

		gameRepository.Update(game)

		json.NewEncoder(w).Encode(game)
	}
}

func joinGameHandler(gameRepository GameRepository) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		gameID := chi.URLParam(r, "GameID")

		var player Player
		json.NewDecoder(r.Body).Decode(&player)

		game, err := gameRepository.FindByID(gameID)
		if err != nil {
			http.Error(w, err.Error(), http.StatusBadRequest)
			return
		}

		game.AddPlayer(&player)

		gameRepository.Update(game)

		json.NewEncoder(w).Encode(game)
	}
}
