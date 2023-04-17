// Dependency Injection Class

package de.htwg.se

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controller.controllerComponent.*
import de.htwg.se.model.fieldComponent.*
import util.*
import de.htwg.se.model.fieldComponent.gameState.{Matrix, PlayerModeStrategy, TruePlayerState}
import de.htwg.se.model.fileIOComponent.*
import de.htwg.se.model.fieldComponent.fieldElements.Game

class MainModule extends AbstractModule {
  override def configure() = {
    bind(classOf[ControllerInterface]).to(
      classOf[controllerBaseImpl.Controller]
    )
    bind(classOf[FieldInterface])
      .annotatedWith(Names.named("DefField"))
      .toInstance(new Game())
    bind(classOf[FieldInterface]).toInstance(new Game())
    bind(classOf[FileIOInterface]).toInstance(fileIOXMLImpl.FileIO())
  }
}
