package main

import (
	"encoding/json"
	"net/http"

	"github.com/go-chi/chi/v5"
	"github.com/google/uuid"
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

func createGameHandler(w http.ResponseWriter, r *http.Request) {
	game := &Game{ID: uuid.NewString(), GameState: WaitingForPlayers}
	json.NewEncoder(w).Encode(game)
}

func joinGameHandler(w http.ResponseWriter, r *http.Request) {
	gameID, err := uuid.Parse(chi.URLParam(r, "GameID"))
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	var player Player
	json.NewDecoder(r.Body).Decode(&player)

	json.NewEncoder(w).Encode(gameID)
}
