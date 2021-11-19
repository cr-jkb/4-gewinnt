import aview.TUI
import controller.Controller
import model.Field
import model.Stone
@main
def hello: Unit = {
	val eol = sys.props("line.separator")
	println(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung" + eol + "AIN SOFTWARE-ENGINEERING WiSe 21/22" + eol + "        ### GRUPPE 15 ###" + eol + eol + ">  Willkommen zu 4-Gewinnt  <")
	val field = new Field(6, 7, Stone.Empty)
	val controller = Controller(field)
	val tui = TUI(controller)
	tui.run
}