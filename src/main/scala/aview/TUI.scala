package aview

import controller.Controller
import model.Stone
import scala.io.StdIn.readLine
import util.Observer
import java.awt.im.spi.InputMethod

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  val eol = sys.props("line.separator")
  val size1 = controller.field.size
  val size2 = controller.field.size2
  def run =
    print(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung" + eol + "AIN SOFTWARE-ENGINEERING WiSe 21/22" + eol + "        ### GRUPPE 15 ###" + eol + eol + ">  Willkommen zu 4-Gewinnt  <" + eol + controller.field.toString)
    getInputAndPrintLoop()
  override def update: Unit = println(controller.toString)

  def getInputAndPrintLoop(): Unit =
    val input = readLine
    input match
      case "q" =>
      case "computer" => 
        controller.setMode(input)
        println("Modus wurde gewechselt.")
        getInputAndPrintLoop()
      case "player" =>
        controller.setMode(input)
        println("Modus wurde gewechselt.")
        getInputAndPrintLoop()
      case _ => {
        val chars = input.toCharArray
        val stone = chars(0) match
          case 'i' =>
            val x = chars(2).toString.toInt
            val y = chars(4).toString.toInt
            controller.put(x - 1, y - 1)
            getInputAndPrintLoop()
      }

      def handleInput(chars:String) = {
        val stone = chars(0) match
          case 'i' =>
            new Some(insert(chars(2).toString.toInt, chars(4).toString.toInt))
            
          case _ =>
            new None
        
      }

      trait Option[InputOption] {
        def map(match_f:InputMethod => InputMethod): Option[InputMethod]
      }
      case class Some[InputMethod] (val iO:InputOption) extends Option[InputMethod] {
        def map(match_f: InputMethod => InputMethod) = new Some(handleInput(InputOption))
      }
      case class None[InputMethod]() extends Option[InputMethod] {
        def map(match_f: InputMethod => InputMethod) = new None
      } 

      trait InputOption {}

      case class insert(x:Int, y:Int) extends InputOption:
        controller.put(x - 1, y - 1)


