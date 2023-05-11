# Rock, Paper, Scissors for Cloudland

GET /api/games/game-123

// Waiting for Player 2
{
   "player1":{
      "id":"4251d6d7-b8ef-4f7d-8cbf-193adb782407",
   },
}


// Players can choose
{
   "player1":{
      "id":"4251d6d7-b8ef-4f7d-8cbf-193adb782407",
      "choiceState": "pending"
   },
   "player2":{
      "id":"4251d6d7-b8ef-4f7d-8cbf-193adb782401231",
      "choiceState": "done"
   },
}

// Waiting for choices
{
   "player1":{
      "id":"4251d6d7-b8ef-4f7d-8cbf-193adb782407",
   },
   "player2":{
      "id":"4251d6d7-b8ef-4f7d-8cbf-193adb782401231",
   },
}


// Game finished
{
   "id": "my-game-id"
   "gameState": "done",
   "player1":{
      "id":"4251d6d7-b8ef-4f7d-8cbf-193adb782407",
      "choice":"paper"
      "choiceState": "done"
   },
   "player2":{
      "choiceState": "done"
      "id":"computer",
      "choice":"rock"
   },
   "result":{
      "winnerId":"4251d6d7-b8ef-4f7d-8cbf-193adb782407",
      "message":"Paper wraps rock"
   }
}


### Nächste ToDos

- Frontend Anbindung Backend
- Welche (cloud-native) Aspekte heben wir hervor?
   * Konfiguration per Umgebungsvariable
   * Fehlerhandling
   * Datenbankanbindung
- GO Anleitung: Neues Projekt 
- "Teilnehmerpaket"
  - OpenAPI Spec als Startpunkt für die Teilnehmer
  - Hinweise / Nützliche Links
    - Entwicklungsumgebung

  - Frontend
  - Web Präsentation 

- Voraussetzungen / "Previous ToDos"
  - GO Tutorials
  - ...

- Outline für Fishbowl (Learnings / interessante Aspekte)



### Ablauf Workshop (1 Std 45min)

1. Vorstellung der Aufgabe / Web Präsentation
2. Teilung in die Sessions
   1. anleiten: Wie fangen wir an?

x.  ngrok - Player vs Player Session

### Fishbowl (45 min)

