package main

import (
	"log"
	"net/http"

	"github.com/go-chi/chi/v5"
	"github.com/rs/cors"
)

func main() {
	r := chi.NewRouter()

	r.Use(cors.AllowAll().Handler)

	gameRepository := NewInMemoryGameRepository()

	r.Route("/api/v1", func(r chi.Router) {
		r.Post("/games", createGameHandler(gameRepository))
		r.Get("/games/{GameID}", readGameHandler(gameRepository))

		// r.Post("/games/{GameID}/players", joinGameHandler(gameRepository))
		// r.Patch("/games/{GameID}/players/{PlayerID}", playerChooseHandler(gameRepository))
	})

	r.Get("/", func(w http.ResponseWriter, r *http.Request) {
		w.Write([]byte("Hello CloudLand!"))
	})

	err := http.ListenAndServe(":8080", r)
	if err != nil {
		log.Fatal(err.Error())
	}
}
