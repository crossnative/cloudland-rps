package main

import (
	"fmt"
	"log"
	"net/http"

	"github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
	"github.com/kelseyhightower/envconfig"
	"github.com/rs/cors"
)

// Application Configuration
type Config struct {
	Port string `envconfig:"PORT",default:"8080"`
}

func main() {
	r := chi.NewRouter()

	r.Use(middleware.Logger)
	r.Use(middleware.Recoverer)
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

	// Read Configuration from Environment Variables
	var c Config
	err := envconfig.Process("ros", &c)
	if err != nil {
		log.Fatal(err.Error())
	}

	http.ListenAndServe(fmt.Sprintf(":%v", c.Port), r)
}
