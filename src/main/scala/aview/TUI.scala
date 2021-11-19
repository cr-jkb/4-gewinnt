package aview

import controller.Controller
import model.Stone
import scala.io.StdIn.readLine
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  val size1 = 6
  val size2 = 7
  def run =
    println(controller.field.toString)
    getInputAndPrintLoop()
  override def update: Unit = println(controller.toString)

  def getInputAndPrintLoop(): Unit =
    val input = readLine
    input match
      case "q" =>
      case _ => {
        val chars = input.toCharArray
        val stone = chars(0) match
          case 'X' => Stone.X
          case 'x' => Stone.X
          case 'O' => Stone.O
          case 'o' => Stone.O
          case _   => Stone.Empty
        val x = chars(2).toString.toInt
        val y = chars(4).toString.toInt
        controller.put(stone, x - 1, y - 1)
        println(controller.toString)
        getInputAndPrintLoop()
      }