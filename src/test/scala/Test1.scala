import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
val eol = sys.props("line.separator")
class GridSpec extends AnyWordSpec:
  "4-Gewinnt" should {
    "have a scalable separation bar" in {
      spielbrett_separator(1, 1) should be { "+-+" + eol }
      spielbrett_separator(2, 1) should be { "+-+-+" + eol }
      spielbrett_separator(1, 2) should be { "+--+" + eol }
    }

    "have scalable slot cells" in {
      spielbrett_slots(1, 1) should be { "| |" + eol }
      spielbrett_slots(2, 1) should be { "| | |" + eol }
      spielbrett_slots(1, 2) should be { "|  |" + eol }
    }

    "have a valid and scalable game field" in {
      SpielBrett(1, 1, 1) should be { "+-+" + eol + "| |" + eol + "+-+" + eol }
    }
  }
