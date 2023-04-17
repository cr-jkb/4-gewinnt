package model

import org.scalatest.wordspec.AnyWordSpec

class testMoreStuff extends AnyWordSpec {
  "A " + "MyStuff" when {
    "?" should {
      "do this" in (true == true)
    }
  }

}
