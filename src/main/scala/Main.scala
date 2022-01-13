import aview.TUI
import aview.GUI
//import controller.controllerComponent.controllerBaseImpl.Controller
import controller.controllerComponent.ControllerInterface
//import model.fieldComponent.fieldBaseImpl.Field
import com.google.inject.Guice
@main
def main: Unit = {
  val injector = Guice.createInjector(new MainModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  //val controller = Controller(new Field())
  val gui = GUI(controller)
  val tui = TUI(controller)
}