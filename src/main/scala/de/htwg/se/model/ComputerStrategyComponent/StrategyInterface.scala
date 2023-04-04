package de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties

import scala.compiletime.ops.int
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import java.lang.reflect.Field
import scala.util.{Try, Success, Failure}

trait Strategy { // concept uses Strategy Pattern but is in its own Folder
  var globalY: Int = -1
  // abstract var globalField : FieldInterface
  var ComputerStone: Stone = Stone.O

  def put(field: FieldInterface): (Int, Int)

//   def matchx(wantedX: Int, field: FieldInterface): Int = {
//     var low_x = 8 // provocate Crash if not handled
//     try {
//       for (try_x <- field.size - 1 to 0 by -1) { // gehe von unten los
//         if (field.get(try_x, globalY) == Stone.Empty) {
//           low_x = try_x; throw SpaceFound;
//         }
//       }
//       throw FullRow
//     } catch {
//       case SpaceFound => low_x
//       case FullRow    => -1
//     }
//   }

  def matchx(wantedX: Int, field: FieldInterface): Int =
    var low_x = 8
    val result = Try {
      for (try_x <- field.size - 1 to 0 by -1) {
        if (field.get(try_x, globalY) == Stone.Empty) {
          low_x = try_x
          throw SpaceFound
        }
      }
      throw FullRow
    } match {
      case Success(_) => low_x
      case Failure(e) =>
        e match {
          case SpaceFound => low_x
          case FullRow    => -1
        }
    }
    result
}

//easy = random
//medium plays random but follows one win strategy (completes its own 3 connected stones)
//hard blocks 2 connected stones and 3 connected stones (does not prevent schachmatt) - follows one win strategy
//invincible blocks 3 connected stones (predicts schachmatt pattern) - follows multiple win strategies
