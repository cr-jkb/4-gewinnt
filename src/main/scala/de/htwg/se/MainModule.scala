package de.htwg.se

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controller.controllerComponent._
import de.htwg.se.model.fieldComponent._
import de.htwg.se.util._

class MainModule extends AbstractModule {
  override def configure() = {
    bind(classOf[ControllerInterface]).toInstance(controllerBaseImpl.Controller(new fieldBaseImpl.Field()))
  }
}