// Main Class
package de.htwg.se
import com.google.inject.Guice
import controller.controllerComponent.ControllerInterface

object VierGewinnt {
  @main
  def main(): Unit = {
    val injector = Guice.createInjector(new MainModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    /* val gui = new GUI(controller) */
    val tui = new TUI(controller)
    tui.start
  }
}
