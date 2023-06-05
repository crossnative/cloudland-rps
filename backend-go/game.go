package main

import (
	"errors"
	"math/rand"
)

const (
	WaitingForPlayers = "WAITING_FOR_PLAYERS"
	WaitingForChoices = "WAITING_FOR_CHOICES"
	Done              = "DONE"

	ModePlayerVsComputer = "PlayerVsComputer"
)

var ErrGameNotFound = errors.New("game not found")

type GameRepository interface {
	FindByID(id string) (*Game, error)
	Create(g *Game) (*Game, error)
	Update(g *Game) (*Game, error)
}

func choices() []string {
	return []string{
		"rock",
		"paper",
		"scissors",
	}
}

func randomChoice() string {
	return choices()[rand.Intn(3)]
}

type Player struct {
	ID     string `json:"id"`
	Name   string `json:"name"`
	Choice string `json:"choice"`
}

type Result struct {
	WinnerID string `json:"winnerId"`
	Message  string `json:"message"`
}

type Game struct {
	ID        string  `json:"id"`
	GameState string  `json:"gameState"`
	Player1   *Player `json:"player1"`
	Player2   *Player `json:"player2"`
	Result    *Result `json:"result"`
}

func (g *Game) AddPlayer(p *Player) error {
	if g.Player1 == nil {
		g.Player1 = p
	} else if g.Player2 == nil {
		g.Player2 = p
	}

	g.updateState()

	return nil
}

func (g *Game) Choose(playerID string, choice string) {
	if g.Player1.ID == playerID {
		g.Player1.Choice = choice
	} else if g.Player2.ID == playerID {
		g.Player2.Choice = choice
	}

	g.updateState()
}

func (g *Game) PlayVsComputer() {
	g.Player2 = &Player{
		ID:   "computer",
		Name: "Computer",
	}

	g.updateState()
}

func (g *Game) updateState() {
	if g.Player1 == nil || g.Player2 == nil {
		g.GameState = WaitingForPlayers
		return
	}

	if g.Player2.ID == "computer" {
		g.Player2.Choice = randomChoice()
	}

	if g.Player1.Choice == "" || g.Player2.Choice == "" {
		g.GameState = WaitingForChoices
		return
	}

	g.evaluate()
	g.GameState = Done
}

func (g *Game) evaluate() {
	result := Result{}
	if g.Player1.Choice == "scissors" {
		if g.Player2.Choice == "scissors" {
			result.Message = "Draw"
		} else if g.Player2.Choice == "rock" {
			result.WinnerID = g.Player2.ID
			result.Message = "Rock destroys scissors"
		} else if g.Player2.Choice == "paper" {
			result.WinnerID = g.Player1.ID
			result.Message = "Scissor cuts paper"
		}
	} else if g.Player1.Choice == "rock" {
		if g.Player2.Choice == "scissors" {
			result.WinnerID = g.Player1.ID
			result.Message = "Rock destroys scissors"
		} else if g.Player2.Choice == "rock" {
			result.Message = "Draw"
		} else if g.Player2.Choice == "paper" {
			result.WinnerID = g.Player2.ID
			result.Message = "Paper wraps rock"
		}
	} else if g.Player1.Choice == "paper" {
		if g.Player2.Choice == "scissors" {
			result.WinnerID = g.Player2.ID
			result.Message = "Scissors cuts paper"
		} else if g.Player2.Choice == "rock" {
			result.WinnerID = g.Player1.ID
			result.Message = "Paper wraps rock"
		} else if g.Player2.Choice == "paper" {
			result.Message = "Draw"
		}
	}

	g.Result = &result
}
