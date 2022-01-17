/* package de.htwg.se.aview

import de.htwg.se.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.model.fieldComponent.fieldBaseImpl._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import java.io.{BufferedReader, ByteArrayInputStream, ByteArrayOutputStream, PrintStream, StringReader}

class TuiSpec extends AnyWordSpec {
  "TUI of 4-Gewinnt" should {
    val controller = new Controller(new Field())
    val tui = TUI(controller)
    val eol = sys.props("line.separator")

    "run" in {
      val in = new BufferedReader(new StringReader("q"))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          val tui2 = tui
        }
      }
      source.toString should be(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung" + eol + "AIN SOFTWARE-ENGINEERING WiSe 21/22" + eol + "        ### GRUPPE 15 ###" + eol + eol + ">  Willkommen zu 4-Gewinnt  <" + eol + controller.field.toString)
    }

    "have a valid input for stone X" in {
      val in = new BufferedReader(new StringReader("i 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.X)
    }
    "have a valid input for stone O" in {
      val in = new BufferedReader(new StringReader("i 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.O)
    }
    "have a valid input for stone x" in {
      val in = new BufferedReader(new StringReader("i 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.X)
    }
    "have a valid input for stone o" in {
      val in = new BufferedReader(new StringReader("i 1 1" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      tui.size1 should be(6)
      tui.size2 should be(7)
      controller.field.get(0, 0) should be(Stone.O)
    }
    "have a valid input for computer" in {
      val in = new BufferedReader(new StringReader("computer" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      source.toString should be("Modus wurde gewechselt." + eol)
    }
    "have a valid input for player" in {
      val in = new BufferedReader(new StringReader("player" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      source.toString should be("Modus wurde gewechselt." + eol)
    }
    "have a valid undo input" in {
      val in = new BufferedReader(new StringReader("u" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      source.toString should be(controller.toString() + eol + "Undo erfolgreich." + eol)
    }
    "have a valid redo input" in {
      val in = new BufferedReader(new StringReader("r" + eol + "q" + eol))
      val source = new ByteArrayOutputStream()
      val printer = new PrintStream(source)
      Console.withOut(printer) {
        Console.withIn(in) {
          tui.inputLoop()
        }
      }
      source.toString should be(controller.toString() + eol + "Redo erfolgreich." + eol)
    }
  }
}
*/