package de.htwg.se.model
package ComputerStrategyComponent
import scala.compiletime.ops.int
import fieldComponent.FieldInterface
import fieldComponent.fieldBaseImpl.Stone
import java.lang.reflect.Field
import scala.util.{Try, Success, Failure}
import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategyComponent_Impl.SpaceFound
import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategyComponent_Impl.FullRow

trait ComputerStrategy { // concept uses Strategy Pattern but is in its own Folder
  var globalY: Int = -1
  // abstract var globalField : FieldInterface
  var ComputerStone: Stone = Stone.O

  def put(field: FieldInterface): (Int, Int)

  def checkField(field: FieldInterface, pos: (Int, Int)): Boolean = {
    if (
      pos._1 < 0 || pos._1 >= field.sizeOfDimX ||
      pos._2 < 0 || pos._2 >= field.sizeOfDimY
    )
      false;
    else (field.get(pos._1, pos._2) == Stone.Empty)
  }

  //Y counts from bottom(=sizeOfDimY -1) to top(=0)
  def letStoneFallDown(atX: Int, field: FieldInterface): (Int, Int) = {
    var currentYPos = -1;
    val result = Try {
      for (try_y <- field.sizeOfDimY - 1 to 0 by -1) {
        if (field.get(try_y, atX) == Stone.Empty) {
          currentYPos = try_y
          throw SpaceFound
        }
      }
      throw FullRow
    } match {
      case Success(x) => x
      case Failure(e) =>
        e match {
          case SpaceFound => (currentYPos, atX)
          case FullRow    => (-1, -1)
        }
    }
    result
  }

  def matchx(wantedX: Int, field: FieldInterface): Int =
    var low_x = 8
    val result = Try {
      for (try_x <- field.sizeOfDimY - 1 to 0 by -1) {
        if (field.get(try_x, globalY) == Stone.Empty) {
          low_x = try_x
          throw SpaceFound
        }
      }
      throw FullRow
    } match {
      case Success(x) => x
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
