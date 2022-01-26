package de.htwg.se.aview

import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.model.moveComponent.Move
import scala.io.StdIn.readLine
import de.htwg.se.util.Observer
import scala.util.{Try,Success,Failure}

class TUI(controller: ControllerInterface) extends Observer:
  controller.add(this)
  val eol = sys.props("line.separator")
  val size1 = controller.field.size
  val size2 = controller.field.size2
  print(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung" + eol + "AIN SOFTWARE-ENGINEERING WiSe 21/22" + eol + "        ### GRUPPE 15 ###" + eol + eol + ">  Willkommen zu 4-Gewinnt  <" + eol + controller.field.toString)
  //controller.loadField
  inputLoop()

  override def update: Unit = println(controller.toString)
  override def kill: Unit = System.exit(0)
  def inputLoop(): Unit =
    analyseInput(readLine) match
      case None => controller.quit
      case Some(move) => 
        move.m match
          case 'i' => controller.put(move.x, move.y)
          case 'c' => controller.setMode("computer"); println("Modus wurde gewechselt.")
          case 'p' => controller.setMode("player"); println("Modus wurde gewechselt.")
          case 'r' => controller.redo; println("Redo erfolgreich.")
          case 'u' => controller.undo; println("Undo erfolgreich.")
          case 'e' => println("Bitte Eingabe ueberpruefen.")
          case 'b' => println("Moegliche Eingaben: 1-7 - Eingabe war nicht innerhalb des Spielfeldes.")
          case 's' => controller.save; println("Spiel gespeichert.")
          case 'l' => controller.load; println("Spiel geladen.") 
        inputLoop()

  def analyseInput(input: String): Option[Move] = // String Interpretation
    input match
      case "q" => None
      case "r" => Some(Move('r', 0, 0))
      case "u" => Some(Move('u', 0, 0))
      case "singleplayer" | "Singleplayer" => Some(Move('c', 0, 0))
      case "multiplayer" | "Multiplayer" => Some(Move('p', 0, 0))
      case "l" => Some(Move('l', 0, 0))
      case "s" => Some(Move('s', 0, 0))
      case _ => {
        getCharArray(input) match
          case Success(chars) => //länge passt
              getInt(chars(0)) match //integer parsed
                case Success(x) =>
                  if (x > 0 & x <= 7) Some(Move('i', 5, x-1)) else Some(Move('b', 0, 0)) //within bounds
                case Failure(x) => Some(Move('e', 0, 0))            
          case Failure(err) => println("Eingabe entspricht nicht der vorgegebenen Laenge."); Some(Move('e', 0, 0))
      }

  def getCharArray(input: String): Try[Array[Char]] = Try {
    if (input.length != 1)
      return Failure(new ArrayIndexOutOfBoundsException)
    input.toCharArray
  }

  def getInt(input: Char): Try[Int] = Try(input.toString.toInt) // convertable to INT?