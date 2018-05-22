# SquirrelGame

Fehler
- MiniSquirrel wird in aussenewände gespawned obwohl der code nur auf freien pos spawnen darf
- bei laufen des minisquirrels in äußere wand (wird kontrolliert ob das feld frei ist) wirft java.lang.ArrayIndexOutOfBoundsException: -1 aus
- MiniSquirrel springt 'komisch' vermutlich wird nur jeder zweite schritt ausgeführt ( siehe energieausgabe m mini)

wäre gut ToDo
- Idee wie fx eingabe menge an übergebener energie an MiniSquirerl
- 4.4 fast fertige freiwillige aufgabe machen

ToDo
- Bot mini + master (aufgabe 5)
- logging (aufgabe 6)
- extended Spiellogic (aufgabe 6)
- bot API eindeutig festzulegen (aufgabe 6)
- junit (aufgabe 7)

solved Problems
- fixed mastersquirrel negaativ energy
- fixed mini always spawned with 1000 energy
- fixed colours fxui
- fixed consol (string) spawn mini
- fixed mini suicide
- fixed fx control (move, help, exit, spawn mini, energy)

