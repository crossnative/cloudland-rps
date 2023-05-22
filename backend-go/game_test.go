package main

import (
	"testing"

	"github.com/google/uuid"
)

func TestFirstPlayerToGame(t *testing.T) {
	game := &Game{}

	player := &Player{ID: uuid.NewString()}

	game.AddPlayer(player)

	if game.Player1 == nil || game.Player1.ID != player.ID {
		t.Fatalf(`New player %v has not joined as player 1.`, player.ID)
	}

}

func TestSecondPlayerToGame(t *testing.T) {
	game := &Game{
		Player1: &Player{ID: uuid.NewString()},
	}

	player := &Player{ID: uuid.NewString()}

	game.AddPlayer(player)

	if game.Player2 == nil || game.Player2.ID != player.ID {
		t.Fatalf(`New player %v has not joined as player 2.`, player.ID)
	}

}
