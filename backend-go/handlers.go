package main

import (
	"encoding/json"
	"net/http"
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
