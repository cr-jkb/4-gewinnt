package de.htwg.se.model
package ComputerStrategyComponent.ComputerStrategyComponent_Impl

import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategy
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import scala.util.Random
import scala.util.{Try, Success, Failure}
import scala.compiletime.ops.boolean
object SpaceFound extends Exception
object FullRow extends Exception

case class easyStrategy() extends ComputerStrategy {
  override def put(field: FieldInterface) = {
    findRandom(field)
  }

  def checkField(field: FieldInterface, pos: (Int, Int)): Boolean = {
    field.get(pos._1, pos._2) != Stone.Empty
  }

  def findRandom(field: FieldInterface): (Int, Int) = {
    val randomPos = (matchx(Random.nextInt(field.sizeOfDimX), field), 0)
    if checkField(field, randomPos) then randomPos
    else findRandom(field);
  }

}
