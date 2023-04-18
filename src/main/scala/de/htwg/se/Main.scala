// Main Class

import com.google.inject.Guice
import de.htwg.se.TUI
import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.MainModule
import de.htwg.se.GUI

object VierGewinnt {
  @main
  def main: Unit = {
    val injector = Guice.createInjector(new MainModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    val gui = GUI(controller)
    val tui = TUI(controller)
    tui.start
  }
}
