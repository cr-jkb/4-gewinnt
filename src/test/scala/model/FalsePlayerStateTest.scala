package de.htwg.se.model.fieldComponent.fieldBaseImpl

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FalsePlayerStateSpec extends AnyWordSpec {
  "FalsePlayerState" should {
    var field1 = new Field(1, 2, Stone.Empty)
    var field2 = new Field(1, 2, Stone.Empty)
    field2.setPlayer("false")
    field2 = field2.put(0, 0)

    "have a valid put input" in {
      FalsePlayerState().put(0, 0, field1) should be(new ErrorField(field2, "None"))
    }
    "have a invalid put input" in {
      FalsePlayerState().put(0, 0, field2) should be(new ErrorField(field2, "Field already taken"))
    }
  }
}