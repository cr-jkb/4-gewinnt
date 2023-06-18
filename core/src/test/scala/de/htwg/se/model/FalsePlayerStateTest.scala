import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.fieldBaseImpl.FalsePlayerState
import de.htwg.se.model.fieldComponent.fieldBaseImpl.ErrorField

class FalsePlayerStateSpec extends AnyWordSpec {
  "FalsePlayerState" should {
    val field1 = new Field(1, 2, Stone.Empty)
    var field2 = new Field(1, 2, Stone.Empty)
    field2.setPlayer("false")
    field2 = field2.put(0, 0)

    "have a valid put input" in {
      FalsePlayerState().put(0, 0, field1) should be(
        new ErrorField(field2, "None")
      )
    }
    "have a invalid put input" in {
      FalsePlayerState().put(0, 0, field2) should be(
        new ErrorField(field2, "Field already taken")
      )
    }
  }
}
