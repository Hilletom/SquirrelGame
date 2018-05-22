# SquirrelGame

Fehler
- bei laufen des minisquirrels in äußere wand (wird kontrolliert ob das feld frei ist) wirft java.lang.ArrayIndexOutOfBoundsException: -1 aus, da das MiniSquirrel 'komisch' springt da es versucht einen doppelten schritt(confirmed) auszuführen ( siehe energieausgabe m mini)

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
- fixed minisquirrel spawned in außenwänden
- fixed mastersquirrel negaativ energy
- fixed mini always spawned with 1000 energy
- fixed colours fxui
- fixed consol (string) spawn mini
- fixed mini suicide
- fixed fx control (move, help, exit, spawn mini, energy)

