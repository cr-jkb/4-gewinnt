// returns false
package de.htwg.se
package WinDetectorComponent.WinDetectorComponent_Impl
import de.htwg.se.fieldComponent.FieldInterface
import de.htwg.se.fieldComponent.fieldBaseImpl.Stone

case class WinStub()
    extends de.htwg.se.WinDetectorComponent.WinDetectorInterface {
  override def checkWin(field: FieldInterface): (Boolean, Stone) = {
    (false, field.get(0, 0))
  } // checks the whole Field
  override def checkWinFor(field: FieldInterface, player: Stone): (Boolean) = {
    false
  } // checks given Stones only
  override def checkWinWithNumber(
      field: FieldInterface,
      player: Stone
  ): (Boolean, Int, List[(Int, List[(Int, Int)])]) = {
    (false, 1, List((0, List((0, 0)))))
  } // returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
}
