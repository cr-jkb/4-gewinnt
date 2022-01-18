// Main Class
package de.htwg.se

import aview.TUI
import aview.GUI
import controller.controllerComponent.ControllerInterface
import com.google.inject.Guice
@main
def main: Unit = {
  val injector = Guice.createInjector(new MainModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val gui = GUI(controller)
  val tui = TUI(controller)
}