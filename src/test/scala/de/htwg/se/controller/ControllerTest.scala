import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.util.Observer
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.ComputerModeStrategy

class ControllerSpec extends AnyWordSpec {
  "A controller" when {
    "observed by an Observer" should {
      val controller = new Controller(new Field())
      val observer = new Observer {
        var updated: Boolean = false
        var killed: Boolean = false

        override def update: Unit = updated = !updated
        override def kill: Unit = killed = !killed

        override def toString: String = updated.toString
      }

      "notify when insert a chip" in {
        controller.add(observer)
        controller.put(1, 1)
        observer.toString should be("true")
      }
      "notify when redo" in {
        controller.redo
        observer.toString should be("false")
      }
      "notify when undo" in {
        controller.undo
        observer.toString should be("true")
      }

      "after remove should not notify" in {
        controller.remove(observer)
        controller.put(1, 1)
        observer.toString should be("true")
      }
    }
    "a set Mode occurs" should {
      val controller2 = new Controller(new Field())
      "set the Field to this Mode" in {
        controller2.setMode("computer") should be(ComputerModeStrategy())
        // controller2.setMode("player") should be(PlayerModeStrategy())
      }
    }

  }
}
