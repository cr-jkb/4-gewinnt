## Willkommen zum Software-Engineering Projekt der HTWG Konstanz!

### Status & Qualitätskontrolle
Test Coverage: 100% (untested files: Main.scala, GUI.scala)
[![Scala CI](https://github.com/cr-jkb/4-gewinnt/actions/workflows/scala.yml/badge.svg)](https://github.com/cr-jkb/4-gewinnt/actions/workflows/scala.yml)
[![Coverage Status](https://coveralls.io/repos/github/cr-jkb/4-gewinnt/badge.svg)](https://coveralls.io/github/cr-jkb/4-gewinnt)

Spieltitel: 4 Gewinnt \
Autoren: Julian Mennel & Chris Jakob \
Jahr: WiSe 2021-2022 \
#### Comment Notations relate to Java Definitions:
- 'Interfaces' for Traits (Interfaces are used as templates for standarized methods and variables), 'Implemented Interfaces' if there are full working methods inside, Classes, ... \
- You will not find any Object Notations as everything is an object in Scala. Instead you may find '1-Instance Classes' as an alias for case classes. 1-Instance refers to the immutability of those. \
- 'Wrapper Classes' can be also called "State-Holders" or "State-Save-Objects" and by that function like Placeholders that may be used as "Swap-In Classes/Objects". \
\
\
### Anleitung:

#### Wie gewinnen?
Der Spieler der es als erstes schafft 4 Steine seiner eigenen Farbe bzw. in unserem Fall Form (O oder X) entweder diagonal, horizontal oder vertikal zu positionieren gewinnt und wird belohnt mit unserem eigenen Soundtrack. Wer verliert muss stattdessen einen weniger motivierenden Track über sich ergehen lassen. \

#### Wie spielen?
Tippe auf die Steine in der GUI \
oder spiele in der TUI per: \
- Befehl 'i 4 5' => Setze Stein auf x=4,y=5 \
- Befehl 'singleplayer' => Wechsle Modus auf SinglePlayer \
- Befehl 'multiplayer' => Wechsle Modus aus Multiplayer \
- Befehl 'u' => Undo \
- Befehl 'r' => Redo \
- Befehl 'q' => Programm beenden \

___ in production
Notation for good style (agility compatible):
- do not use var, try, null, void (monads instead)


LAYER Order when placing a Stone in /view: (descending = from above)
- View
|
v
- Controller

- Field
- ModeStrategy
- Player
- Stone X or O (placed using field.copy)
