package main

import "github.com/google/uuid"

var _ GameRepository = (*InMemoryGameRepository)(nil)

type InMemoryGameRepository struct {
	games map[string]*Game
}

func NewInMemoryGameRepository() *InMemoryGameRepository {
	return &InMemoryGameRepository{games: make(map[string]*Game)}
}

func (r *InMemoryGameRepository) FindByID(id string) (*Game, error) {
	if r.games[id] == nil {
		return nil, ErrGameNotFound
	}
	return r.games[id], nil
}

func (r *InMemoryGameRepository) Create(g *Game) (*Game, error) {
	g.ID = uuid.NewString()
	r.games[g.ID] = g
	return g, nil
}

func (r *InMemoryGameRepository) Update(g *Game) (*Game, error) {
	r.games[g.ID] = g
	return g, nil
}
