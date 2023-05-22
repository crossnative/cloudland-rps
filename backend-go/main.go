package main

import (
	"net/http"

	"github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
	"github.com/rs/cors"
)

func main() {
	r := chi.NewRouter()

	r.Use(middleware.Logger)
	r.Use(cors.AllowAll().Handler)

	gameRepository := NewInMemoryGameRepository()

	r.Route("/api/v1", func(r chi.Router) {
		r.Post("/play", playComputerHandler)
		r.Post("/games", createGameHandler(gameRepository))
		r.Get("/games/{GameID}", readGameHandler(gameRepository))
		r.Post("/games/{GameID}/players", joinGameHandler(gameRepository))
		r.Patch("/games/{GameID}/players/{PlayerID}", playerChooseHandler(gameRepository))
	})

	r.Get("/", func(w http.ResponseWriter, r *http.Request) {
		w.Write([]byte("Hello Cloudland!"))
	})

	http.ListenAndServe(":8080", r)
}
