package aview

import controller.Controller
import model.Stone
import model.Move
import scala.io.StdIn.readLine
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  val eol = sys.props("line.separator")
  val size1 = controller.field.size
  val size2 = controller.field.size2
  def run =
    print(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung" + eol + "AIN SOFTWARE-ENGINEERING WiSe 21/22" + eol + "        ### GRUPPE 15 ###" + eol + eol + ">  Willkommen zu 4-Gewinnt  <" + eol + controller.field.toString)
    inputLoop()
  override def update: Unit = println(controller.toString)

  def inputLoop(): Unit =
    analyseInput(readLine) match
      case None =>
      case Some(move) => 
        move.m match
          case 'i' => controller.put(move.x, move.y)
          case 'c' => controller.setMode("computer"); println("Modus wurde gewechselt.")
          case 'p' => controller.setMode("player"); println("Modus wurde gewechselt.")
          case 'r' => controller.redo
          case 'u' => controller.undo
        inputLoop()

  def analyseInput(input: String): Option[Move] =
    input match
      case "q" => None
      case "r" => Some(Move('r', 0, 0))
      case "u" => Some(Move('u', 0, 0))
      case "computer" => Some(Move('c', 0, 0))
      case "player" => Some(Move('p', 0, 0))
      case _ => {
        val chars = input.toCharArray
        chars(0) match
          case 'i' =>
            val x = chars(2).toString.toInt
            val y = chars(4).toString.toInt
            Some(Move('i', x - 1, y - 1))
          case _ => None
      }
