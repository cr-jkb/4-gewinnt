import aview.TUI
import aview.GUI
import controller.Controller
import model.Field
import model.Stone
@main
def hello: Unit = {
  val field = new Field()
  val controller = Controller(field)
  val gui = new GUI(controller)
  val tui = TUI(controller)
  tui.run
}