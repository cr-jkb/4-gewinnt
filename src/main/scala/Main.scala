import aview.TUI
import aview.GUI
import controller.controllerComponent.controllerBaseImpl.Controller
import model.fieldComponent.fieldBaseImpl.Field
@main
def main: Unit = {
  val controller = Controller(new Field())
  val gui = GUI(controller)
  val tui = TUI(controller)
}