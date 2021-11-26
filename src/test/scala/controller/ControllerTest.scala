package controller

import util.Observer
import model.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class ControllerSpec extends AnyWordSpec {
  "A controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false

        override def update: Unit = updated = !updated

        override def toString: String = updated.toString
      }

      "notify when insert a chip" in {
        controller.add(observer)
        controller.put(1, 1)
        observer.toString should be("true")
      }

      "after remove should not notify" in {
        controller.remove(observer)
        controller.put(1, 1)
        observer.toString should be("true")
      }
    }
  }
}
