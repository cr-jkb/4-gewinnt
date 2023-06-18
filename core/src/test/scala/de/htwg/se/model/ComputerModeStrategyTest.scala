import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.fieldBaseImpl.ComputerModeStrategy
import de.htwg.se.model.fieldComponent.fieldBaseImpl.ErrorField

class ComputerModeStrategySpec extends AnyWordSpec {
  "ComputerModeStrategy" should {
    val field1 = new Field(1, 2, Stone.Empty)
    val field2 = new Field(1, 2, Stone.Empty).put(0, 0).put(0, 1)
    val field3 = new Field(1, 2, Stone.Empty)
    val field4 = new Field(1, 2, Stone.Empty).put(0, 1)
    /* field2 = field2.put(0, 0) */
    /* field2 = field2.put(0, 1) */
    /* field4 = field4.put(0, 1) */

    "have a valid computer input" in {
      ComputerModeStrategy().put(0, 0, field1) should be( // TODO23
        new ErrorField(field2, "None")
      )
    }
    "have a invalid computer input" in {
      ComputerModeStrategy().put(0, 1, field3) should be(
        new ErrorField(field4, "None")
      )
    }
  }
}
