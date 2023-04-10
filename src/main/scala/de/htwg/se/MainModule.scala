// Dependency Injection Class

package de.htwg.se

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controller.controllerComponent._
import de.htwg.se.model.fieldComponent._
import de.htwg.se.util._
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Matrix
import de.htwg.se.model.fieldComponent.fieldBaseImpl.TruePlayerState
import de.htwg.se.model.fieldComponent.fieldBaseImpl.PlayerModeStrategy
import de.htwg.se.model.fileIOComponent._
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field

class MainModule extends AbstractModule {
  override def configure() = {
    bind(classOf[ControllerInterface]).to(
      classOf[controllerBaseImpl.Controller]
    )
    bind(classOf[FieldInterface])
      .annotatedWith(Names.named("DefField"))
      .toInstance(new Field())
    bind(classOf[FieldInterface]).toInstance(new fieldBaseImpl.Field())
    bind(classOf[FileIOInterface]).toInstance(fileIOXMLImpl.FileIO())
  }
}
