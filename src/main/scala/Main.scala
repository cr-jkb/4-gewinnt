import aview.TUI
import aview.GUI
import controller.Controller
import model.Field
import model.Stone
@main
def main: Unit = {
  val controller = Controller(new Field())
  val gui = GUI(controller)
  val tui = TUI(controller)
}