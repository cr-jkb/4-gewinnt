import aview.TUI
import controller.Controller
import model.Field
import model.Stone
@main
def hello: Unit = {
	val field = new Field(6, 7, Stone.Empty)
	val controller = Controller(field)
	val tui = TUI(controller)
	tui.run
}