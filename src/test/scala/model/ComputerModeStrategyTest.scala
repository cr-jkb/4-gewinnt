package de.htwg.se.model.fieldComponent.gameState

import de.htwg.se.model.fieldComponent.fieldElements.Game
import de.htwg.se.util.Stone
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ComputerModeStrategySpec extends AnyWordSpec {
  "ComputerModeStrategy" should {
    val field1 = new Game(1, 2, Stone.Empty)
    val field2 = new Game(1, 2, Stone.Empty).put(0, 0).put(0, 1)
    val field3 = new Game(1, 2, Stone.Empty)
    val field4 = new Game(1, 2, Stone.Empty).put(0, 1)
    /* field2 = field2.put(0, 0) */
    /* field2 = field2.put(0, 1) */
    /* field4 = field4.put(0, 1) */

    "have a valid computer input" in {
      ComputerModeStrategy().put(0, 0, field1) should be( //TODO23
        (field2, "None")
      )
    }
    "have a invalid computer input" in {
      ComputerModeStrategy().put(0, 1, field3) should be(
        (field4, "None")
      )
    }
  }
}
