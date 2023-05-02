package de.htwg.se

import de.htwg.se.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.model.fieldComponent.fieldBaseImpl._
import com.google.inject.Guice
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import java.io.{
  BufferedReader,
  ByteArrayInputStream,
  ByteArrayOutputStream,
  PrintStream,
  StringReader
}
import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.model.moveComponent.Move
import scala.util.Failure
import scala.util.Success

class TuiSpec extends AnyWordSpec {
  "TUI of 4-Gewinnt" should {
    val injector = Guice.createInjector(new MainModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    val tui = TUI(controller)
    val eol = sys.props("line.separator")

    "be able to analyse 'q' input" in {
      tui.analyseInput("q") should be(None)
    }
    "be able to analyse 'r' input" in {
      tui.analyseInput("r") should be(Some(Move('r', 0, 0)))
    }
    "be able to analyse 'u' input" in {
      tui.analyseInput("u") should be(Some(Move('u', 0, 0)))
    }
    "be able to analyse 'Singelpayer' or 'singleplayer' input" in {
      tui.analyseInput("singleplayer") should be(Some(Move('c', 0, 0)))
      tui.analyseInput("Singleplayer") should be(Some(Move('c', 0, 0)))
    }
    "be able to analyse 'Multipayer' or 'multiplayer' input" in {
      tui.analyseInput("multiplayer") should be(Some(Move('p', 0, 0)))
      tui.analyseInput("Multiplayer") should be(Some(Move('p', 0, 0)))
    }
    "be able to analyse 's' input" in {
      tui.analyseInput("s") should be(Some(Move('s', 0, 0)))
    }
    "be able to analyse 'l' input" in {
      tui.analyseInput("l") should be(Some(Move('l', 0, 0)))
    }
    "be able to analyse a valid input" in {
      tui.analyseInput("1") should be(Some(Move('i', 5, 0)))
    }
    "be able to analyse a invalid input" in {
      tui.analyseInput("10") should be(Some(Move('e', 0, 0)))
      tui.analyseInput("9") should be(Some(Move('b', 0, 0)))
    }

    "have a getInt function" in {
      tui.getIntFrom('1') should be(Success(1))
    }
  }
}
