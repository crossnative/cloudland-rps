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

	r.Route("/api/v1", func(r chi.Router) {
		r.Post("/play", playComputerHandler)
		r.Post("/games", createGameHandler)
		r.Post("/game/{GameID}/players", joinGameHandler)
	})

	r.Get("/", func(w http.ResponseWriter, r *http.Request) {
		w.Write([]byte("Hello Cloudland!"))
	})

	http.ListenAndServe(":8080", r)
}
