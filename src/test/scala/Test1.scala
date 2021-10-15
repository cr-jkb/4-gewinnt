import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class GridSpec extends AnyWordSpec:
  "4-Gewinnt" should {
    "have a grid as String of form 7x6" in {
      spielbrett() should be("+---+---+---+---+---+---+---+") + eol
    }
  }
