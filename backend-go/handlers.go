package main

import (
	"encoding/json"
	"net/http"

	"github.com/go-chi/chi/v5"
)

func createGameHandler(gameRepository GameRepository) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		game, _ := gameRepository.Create(NewGame())

		mode := r.URL.Query().Get("mode")
		if mode == ModePlayerVsComputer {
			game.PlayVsComputer()
		}

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
