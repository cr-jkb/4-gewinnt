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

    "have scalable slot cells " in {
      spielbrett_slots(1, 1) should be { "| |" + eol }
      spielbrett_slots(2, 1) should be { "| | |" + eol }
      spielbrett_slots(1, 2) should be { "|  |" + eol }
    }

    "have a valid and scalable game field" in {
      SpielBrett(1, 1, 1) should be { "+-+" + eol + "| |" + eol + "+-+" + eol }
    }
    
    "have a input process" in {
      processInputLine("q", "+-+" + eol + "|X|" + eol + "+-+" + eol, 1, 1, 1) should be { "+-+" + eol + "|X|" + eol + "+-+" + eol }
      processInputLine("n", "+-+" + eol + "|X|" + eol + "+-+" + eol, 1, 1, 1) should be { "+-+" + eol + "| |" + eol + "+-+" + eol }
      processInputLine("i", "+-+" + eol + "| |" + eol + "+-+" + eol, 1, 1, 1) should be { "+-+" + eol + "|X|" + eol + "+-+" + eol }
    }

    "have a valid check" in {
      checkValid(1, 1, 1, 1, 1, "+-+" + eol + "| |" + eol + "+-+" + eol) should be { true }
      checkValid(1, 1, 1, 1, 1, "+-+" + eol + "|X|" + eol + "+-+" + eol) should be { false }
      checkValid(1, 1, 1, 1, 1, "+-+" + eol + "|O|" + eol + "+-+" + eol) should be { false }
    }
  }
