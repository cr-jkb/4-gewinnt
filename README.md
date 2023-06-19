
<h1 align="center">Willkommen zu Vier Gewinnt</h1><h2 align="center">Software-Architektur Projekt</h2> <h3 align="center">aus SPO3 bei AIN6 an der HTWG Konstanz</h3>

<p align="center">im Jahr: SoSe 2023</p>
<h4 align="center">4 Gewinnt + FileIO Service <br>  Chris Jakob & Yasmin Hoffmann </br> <br> </h3>

<p align="center">
  <img src="https://github.com/cr-jkb/4-gewinnt/actions/workflows/scala.yml/badge.svg" /> 
  <a href="https://coveralls.io/github/cr-jkb/4-gewinnt">
    <img src="https://coveralls.io/repos/github/cr-jkb/4-gewinnt/badge.svg?branch=master" alt='Coverage Status' />
  </a>
 </p>

<h2>ㅤ</h2>
<h3 align="center"> Ursprung und Aufbau</h3>

#

Dieses Projekt basiert auf den Grundlagen des Software-Engineering Projektes von<br/>Chris Jakob und Julian Mennel im Wintersemester 2021-2022.
<br/>

Im Verlaufe der Vorlesung Softwarearchitektur an der HTWG im Sommersemester 2023 wurde dieses Vier-Gewinnt um einen **FileIO Microservice** erweitert, der unabhängig von dem Spiel ausgeführt wird und mit dem über eine **REST API Schnittstelle** kommuniziert werden kann. <br/> Dieser Microservice kümmert sich um das Speichern und Laden von Spielständen. Diese Persistenzfunktionen wurden jeweils für die Datenbanken **MongoDB und PostgreSQL** implementiert. <br/>In der Datei 
[fileIOAPI.scala] 
kann dabei ausgewählt werden, auf welche Datenbank zugegriffen werden soll.<br/>
Beide Implementierungen wurden zudem mit verschiedensten Gatling Simulationen auf ihre Performance getestet, deren Resultate im [results] Ordner eingesehen werden können.   

<br/>

### Spiel kann gestartet werden via*:
- **sbt:** <br/>
cd core <br/>
sbt run
- **docker:** docker build image . / docker run image

(* beide Befehle werden für den vollständigen Funktionsumfang benötigt)

<br/>

### Zur weiteren Ordnerstruktur:
- **.(Dev Material):** enthält Scripte und Archive mit DevTools
- **.bloop, .bsp, .github, .idea, .metals, .vscode:** enthalten Daten für die verwendeten BuildTools, Versionsverwaltung, IDEs und Scala
- **core:** enthält unter /src und /res den Code und das Modul für 4-Gewinnt.
- **fileIOService:** enthält unter /src den Code für den Mikroservice sowie den Dockerfile (Compose File) zum Starten in Docker.
<br/>Unser Mikroservice speichert den Spielstand, wenn gewollt, auch lokal auf der Festplatte, und erstellt dabei /game.json in diesem Ordner.
- **gatling-3.9.5:** enthält Gatling Programmdaten, Performance Test Simulationen und die dazugehörigen Ergebnisse für jeweils MongoDB und postgreSQL
- **project:** wird von Scala automatisch generiert
- **target:** wird von Scala automatisch generiert

<br/>


# Anleitung:

### Wie gewinnen?
Der Spieler der es als erstes schafft 4 Steine seiner eigenen Farbe entweder diagonal, horizontal oder vertikal zu positionieren gewinnt und wird belohnt mit unserem eigenen Soundtrack. Wer verliert muss stattdessen einen weniger motivierenden Track über sich ergehen lassen. 

### Wie spielen?
Nutze unsere herausragende GUI \
oder spiele in der TUI per: 
Command | Description 
--------|--------
 `1` ... `7`  | Setze Stein auf die eingegebene Reihe 
 singleplayer | Wechsle den Modus auf SinglePlayer 
 easy         | Setze Stärke des Computers auf Einfach
 medium       | Setze Stärke des Computers auf Medium
 hard         | Setze Stärke des Computers auf Schwer (nicht fertig)
 invincible   | Setze Stärke des Computers auf Maximum (nicht fertig)
 multiplayer  | Wechsle den Modus auf Multiplayer 
 save         | Exportiere den aktuellen Spielzustand
 load         | Importiere einen Spielstand aus XML oder JSON
 undo         | Undo 
 redo         | Redo
 q            | Programm beenden
 
 Das Spiel reagiert auch auf halbe Befehle: z.B. s für save und multi für Multiplayer
 ## TUI
 ![tuiImage](/core/TUI.png)
 
 ## GUI
 ![guiImage](/core/GUI.png)

 ## Authors
[@Chris Jakob](https://github.com/cr-jkb "Chris sein GitHub") <br/>
[@Yasmin Hoffmann](https://github.com/yasmoonx "Yasmin ihr GitHub") <br>
[@Julian Mennel](https://github.com/JulianMennel "Julian sein GitHub")<br>

[fileIOAPI.scala]: /fileIOService/src/main/scala/de/htwg/se/fileIOAPI.scala
[results]:/gatling-3.9.5/results/

[./4-gewinnt/fileIOService/src/main/scala/de/htwg/se/fileIOAPI.scala]<br>
[./4-gewinnt/gatling-3.9.5/results]


<br/>

# Dependencies:
Unser Programm wurde mit folgenden Tools gebaut und ist daher mit den folgenden Programmen und Versionsnummern kompatibel:

## Tools:
- **Windows 11 Edu**
- **Java JDK 17.0.2**
- **Scala 3.2.2**
- **Sbt 1.8.2**
- **Metals 0.11.11 (VS Code ext 1.22.0)**
- **Bloop 1.5.6**
- **VS Code 1.77.0**

<br/>

- **Guice 5.1.1**
- **JUnit 0.13.3**
- **Scala-XML 2.1.0**
- **Play Server 2.10.0-RC7**