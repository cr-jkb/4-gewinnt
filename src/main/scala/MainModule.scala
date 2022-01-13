import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import controller.controllerComponent._

class MainModule extends AbstractModule with ScalaModule {
  def configure() = {
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
  }
}