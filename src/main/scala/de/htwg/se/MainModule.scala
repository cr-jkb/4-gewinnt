// Dependency Injection Class
package de.htwg.se

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
class MainModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).to(
      classOf[Controller]
    )
    bind(classOf[FieldInterface])
      .annotatedWith(Names.named("DefField"))
      .toInstance(new Field())
    bind(classOf[FieldInterface]).toInstance(new Field())
    // bind(classOf[FileIOInterface]).toInstance(fileIOXMLImpl.FileIO())
  }
}
