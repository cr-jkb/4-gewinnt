/*package vierGewinnt.view*/
package de.htwg.se.aview
import de.htwg.globals.*

import de.htwg.se.controller.controllerComponent.ControllerInterface
import util.Observer
import scala.io.StdIn.readLine
import scala.util.{Try, Success, Failure}

class TUI(controller: ControllerInterface) extends Observer:
  controller.add(this)
  override def update: Unit = println(controller.toString)
  override def kill: Unit = System.exit(0);

  def start =
    print(
      eol +
        "Hochschule fuer Technik, Wirtschaft & Gestaltung" + eol +
        "AIN SOFTWARE-ENGINEERING WiSe 21/22" + eol +
        "        ### GRUPPE 15 ###" + eol + eol +
        ">  Willkommen zu 4-Gewinnt  <" + eol +
        controller.gameState.toString
    )
    inputLoop()

  def inputLoop(): Unit =
    analyseInput(readLine) match
      case None => controller.quit
      case Some(move) =>
        move.m match
          case 'i' => controller.put(move.x, move.y); println(controller.CommandFeedbackPipe)
          case 'c' =>
            controller.setMode("computer"); println("Modus wurde gewechselt.")
          case 'p' =>
            controller.setMode("player"); println("Modus wurde gewechselt.")
          case 'r' => controller.redo; println(controller.CommandFeedbackPipe)
          case 'u' => controller.undo; println(controller.CommandFeedbackPipe)
          case 'e' => println("Bitte Eingabe ueberpruefen.")
          case 'b' =>
            println(
              "Moegliche Eingaben: 1-7 - Eingabe war nicht innerhalb des Spielfeldes."
            )
          case 's' => controller.save; println("Spiel gespeichert.")
          case 'l' => controller.load; println("Spiel geladen.")
          case 'S' => controller.setStrength(move.x)
        inputLoop()

  def analyseInput(input: String): Option[Move] = // String Interpretation
    input match
      case "q"                             => None
      case "r"                             => Some(Move('r', 0, 0))
      case "u"                             => Some(Move('u', 0, 0))
      case "singleplayer" | "Singleplayer" => Some(Move('c', 0, 0))
      case "multiplayer" | "Multiplayer"   => Some(Move('p', 0, 0))
      case "easy"                          => Some(Move('S', 0, 0))
      case "medium"                        => Some(Move('S', 1, 0))
      case "hard" | "difficult"            => Some(Move('S', 2, 0))
      case "invincible"                    => Some(Move('S', 3, 0))
      case "l"                             => Some(Move('l', 0, 0))
      case "s"                             => Some(Move('s', 0, 0))
      case _ => {
        splitIntoChars(input) match
          case Success(chars) => //Länge passt
            getIntFrom(chars(0)) match //Integer geparsed
              case Success(x) =>
                if (x > 0 & x <= 7) Some(Move('i', 5, x - 1))
                else Some(Move('b', 0, 0)) //not within bounds
              case Failure(x) => Some(Move('e', 0, 0))
          case Failure(err) => Some(Move('e', 0, 0))
      }

  def splitIntoChars(input: String): Try[Array[Char]] = {
    if (input.length != 1) then Failure(new ArrayIndexOutOfBoundsException)
    else Try(input.toCharArray)

  }

  def getIntFrom(input: Char): Try[Int] = Try(
    input.toString.toInt
  )


case class Move(m: Char, x: Int, y: Int)