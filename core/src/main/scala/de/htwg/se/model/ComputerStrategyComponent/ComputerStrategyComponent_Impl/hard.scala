package de.htwg.se.model
package ComputerStrategyComponent.ComputerStrategyComponent_Impl

import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategy
import de.htwg.se.model.fieldComponent.FieldInterface
import org.scalactic.Bool

case class difficultStrategy() extends ComputerStrategy {
  var plannedNext = (0, 0)
  var alertNext = (-1, 0)
  val alertVal = 2
  override def put(field: FieldInterface) = {
    /* if (evalNext) plannedNext
    else if (alertNext != (-1, -1)) alertNext
    else evalNew */
    1
  }

  /* def evalNext: Boolean = { // If still best solution
    alertNext = (-1, 0)
    if (!listenToAlert) {
      true
    } else false
  }

  def listenToAlert: Boolean = { // boolean unnecessary
    val enemyMax = 2
    if (enemyMax >= 2) {
      alertNext = (0, 0) // prediction of Alert
      true // strategy in plannedNext preserved
    } else false
  }

  def evalNew: (Int, Int) = { // try getting 3 and 4
    (0, 0)
  } */
}
