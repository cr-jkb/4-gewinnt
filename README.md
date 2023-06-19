
<h1 align="center">Willkommen zum Software-Architektur Projekt</h1> <h3 align="center">aus SPO3 bei AIN6 an der HTWG Konstanz</h3>

<p align="center">Jahr: SoSe 2023</p>
<h4 align="center">4 Gewinnt + FileIO Service <br>  Chris Jakob & Yasmin Hoffmann </br> <br> </h3>

<p align="center">
  <img src="https://github.com/cr-jkb/4-gewinnt/actions/workflows/scala.yml/badge.svg" /> 
  <a href="https://coveralls.io/github/cr-jkb/4-gewinnt">
    <img src="https://coveralls.io/repos/github/cr-jkb/4-gewinnt/badge.svg?branch=master" alt='Coverage Status' />
  </a>
 </p>
 
# Architektur:   
Dieses Projekt basiert auf dem Software-Engineering Projekt von Chris Jakob und Julian Mennel aus dem WiSe 2021-2022.   
Im Laufe des Semesters wurde 4-Gewinnt um einen FileIO Microservice erweitert, der komplett unabhängig von dem Spiel ist und mit dem über eine API kommuniziert werden kann. Der Microservice kümmert sich um das saven und loaden von Spielständen. Diese Funktionen wurden für MongoDB oder Postgres implementiert. In fileIOService/src/main/scala/de/htwg/se/fileIOAPI.scala kann jeweils ausgewählt werden, welche Datenbank verwendet werden soll.
Beide Implementierungen wurden zudem mit verschiedenen Gatling Simulationen getestet, deren Resultate sich im gatling Ordner befinden.   
- **sbt:** sbt run / clean / compile / test
- **docker:** docker build image . / docker run image



# Anleitung:

### Wie gewinnen?
Der Spieler der es als erstes schafft 4 Steine seiner eigenen Farbe entweder diagonal, horizontal oder vertikal zu positionieren gewinnt und wird belohnt mit unserem eigenen Soundtrack. Wer verliert muss stattdessen einen weniger motivierenden Track über sich ergehen lassen. 

### Wie spielen?
Nutze unsere herausragende GUI \
oder spiele in der TUI per: 
Command | Description 
--------|--------
 `1` ... `7`  | Setze Stein auf eingegebene Reihe 
 singleplayer | Wechsle Modus auf SinglePlayer 
 easy         | Setze Stärke des Computers auf Einfach
 medium       | Setze Stärke des Computers auf Medium
 hard         | Setze Stärke des Computers auf Schwer
 invincible   | Setze Stärke des Computers auf Maximum
 multiplayer  | Wechsle Modus aus Multiplayer 
 save         | Exportiere den aktuellen Spielzustand
 load         | Importiere einen Spielstand aus XML oder JSON
 undo         | Undo 
 redo         | Redo
 q            | Programm beenden
 
 ## TUI
 
 ## GUI
 
 ## Authors
[@Chris Jakob](https://github.com/cr-jkb "Chris sein GitHub") <br/>
[@Yasmin Hoffmann](https://github.com/yasmoonx "Yasmin ihr GitHub") <br>
[@Julian Mennel](https://github.com/JulianMennel "Julian sein GitHub")
