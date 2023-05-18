package main

import "math/rand"

const (
	WaitingForPlayers = "WAITING_FOR_PLAYERS"
	WaitingForChoices = "WAITING_FOR_CHOICES"
	Done              = "DONE"
)

func choices() []string {
	return []string{
		"rock",
		"paper",
		"scissor",
	}
}

func randomChoice() string {
	return choices()[rand.Intn(3)]
}

type Player struct {
	ID     string `json:"id"`
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

func (g *Game) PlayVsComputer() {
	g.Player2 = &Player{
		ID:     "computer",
		Choice: randomChoice(),
	}

	g.Evaluate()
}

func (g *Game) Evaluate() {
	result := Result{}
	if g.Player1.Choice == "scissor" {
		if g.Player2.Choice == "scissor" {
			result.Message = "Draw"
		} else if g.Player2.Choice == "rock" {
			result.WinnerID = g.Player2.ID
			result.Message = "Rock destroys scissor"
		} else if g.Player2.Choice == "paper" {
			result.WinnerID = g.Player1.ID
			result.Message = "Scissor cuts paper"
		}
	} else if g.Player1.Choice == "rock" {
		if g.Player2.Choice == "scissor" {
			result.WinnerID = g.Player1.ID
			result.Message = "Rock destroys scissor"
		} else if g.Player2.Choice == "rock" {
			result.Message = "Draw"
		} else if g.Player2.Choice == "paper" {
			result.WinnerID = g.Player2.ID
			result.Message = "Paper wraps rock"
		}
	} else if g.Player1.Choice == "paper" {
		if g.Player2.Choice == "scissor" {
			result.WinnerID = g.Player2.ID
			result.Message = "Scissor cuts paper"
		} else if g.Player2.Choice == "rock" {
			result.WinnerID = g.Player1.ID
			result.Message = "Paper wraps rock"
		} else if g.Player2.Choice == "paper" {
			result.Message = "Draw"
		}
	}

	g.Result = &result
}
