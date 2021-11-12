package aview

import controller.Controller
import util.Observer
import scala.io.StdIn.readLine
import java.util.Scanner

var spieler: Boolean = false

class TUI(controller: Controller) extends Observer:
	controller.add(this)
	def run =
		println(controller.field)
		getInputAndPrintLoop(controller)
	override def update = ???

def getInputAndPrintLoop(controller: Controller): Unit =
	input = readLine()
	input match {
		case "q" =>
		case "n" => 
			controller.reset()
			println(controller.field)
			getInputAndPrintLoop(controller)
		case "i" =>
			val line = new Scanner(System.in)
			val userLine = line.nextInt 
			val userRow = line.nextInt
			controller.put(spieler, userLine, userRow)
			println(controller.field)
			getInputAndPrintLoop(controller)
	}