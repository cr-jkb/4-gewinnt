package aview

import controller.Controller
import model.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import java.io.{BufferedReader, ByteArrayInputStream, ByteArrayOutputStream, PrintStream, StringReader}

class TuiSpec extends AnyWordSpec {
  "TUI of 4-Gewinnt" should {
    val controller = new Controller()
    val tui = new TUI(controller)
    val eol = sys.props("line.separator")

    "run" in {
      val in = new BufferedReader(new StringReader("q"))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.run
        }
      }
      source.toString should be(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung" + eol + "AIN SOFTWARE-ENGINEERING WiSe 21/22" + eol + "        ### GRUPPE 15 ###" + eol + eol + ">  Willkommen zu 4-Gewinnt  <" + eol + controller.field.toString)
    }

    "have a valid input for stone X" in {
      val in = new BufferedReader(new StringReader("X 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.getInputAndPrintLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.X)
    }
    "have a valid input for stone x" in {
      val in = new BufferedReader(new StringReader("x 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.getInputAndPrintLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.X)
    }
    "have a valid input for stone O" in {
      val in = new BufferedReader(new StringReader("O 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.getInputAndPrintLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.O)
    }
    "have a valid input for stone o" in {
      val in = new BufferedReader(new StringReader("o 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.getInputAndPrintLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.O)
    }
  }
}