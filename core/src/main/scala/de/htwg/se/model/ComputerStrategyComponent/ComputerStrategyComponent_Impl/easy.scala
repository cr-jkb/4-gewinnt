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

  def findRandom(field: FieldInterface): (Int) = {
    val randomPos = (matchx(Random.nextInt(field.sizeOfDimX), field))
    if checkField(field, randomPos) then randomPos
    else findRandom(field);
  }

}
