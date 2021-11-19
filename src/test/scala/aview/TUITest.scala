package aview

import controller.Controller
import model.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class TuiSpec extends AnyWordSpec {
  "TUI of VierGewinnt" should {
    val controller = new Controller()
    val tui = new TUI(controller)

    "valid input" in {
      controller.put(Stone.X, 1, 1)
      tui.size1 should be(6)
      tui.size2 should be(7)
    }
  }
}