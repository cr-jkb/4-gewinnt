package de.htwg.se.model.fieldComponent.gameState

import de.htwg.se.model.fieldComponent.fieldElements.Game
import de.htwg.se.util.Stone
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FalsePlayerStateSpec extends AnyWordSpec {
  "FalsePlayerState" should {
    val field1 = new Game(1, 2, Stone.Empty)
    var field2 = new Game(1, 2, Stone.Empty)
    field2.setPlayer("false")
    field2 = field2.put(0, 0)

    "have a valid put input" in {
      FalsePlayerState().put(0, 0, field1) should be(
        (field2, "None")
      )
    }
    "have a invalid put input" in {
      FalsePlayerState().put(0, 0, field2) should be(
        (field2, "Game already taken")
      )
    }
  }
}
