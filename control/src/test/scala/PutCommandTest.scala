import de.htwg.se.fieldComponent.fieldBaseImpl.Field
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.controller.controllerComponent.controllerBaseImpl.PutCommand
import de.htwg.se.controller.controllerComponent.controllerBaseImpl.Controller

class PutCommandSpec extends AnyWordSpec {
  "PutCommand" should {

    val field = new Field()
    val field2 = new Field()
    field2.put(0, 0)
    val putCommand = new PutCommand(0, 0, new Controller(new Field))

    "have a noStep function" in {
      putCommand.noStep(field) should be(field)
    }
  }
} //TODO23
