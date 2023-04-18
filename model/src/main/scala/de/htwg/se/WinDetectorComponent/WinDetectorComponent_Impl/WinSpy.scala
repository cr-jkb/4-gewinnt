// returns true each alertAfter
package de.htwg.se
package WinDetectorComponent.WinDetectorComponent_Impl
import de.htwg.se.fieldComponent.FieldInterface
import de.htwg.se.fieldComponent.fieldBaseImpl.Stone

case class WinSpy(alertAfter: Int)
    extends de.htwg.se.WinDetectorComponent.WinDetectorInterface {
  var countedCalls = 0

  override def checkWin(field: FieldInterface): (Boolean, Stone) = {
    countedCalls = countedCalls + 1;
    ((countedCalls % alertAfter == 0), field.get(0, 0))
  } // checks the whole Field
  override def checkWinFor(field: FieldInterface, player: Stone): (Boolean) = {
    countedCalls = countedCalls + 1; (countedCalls % alertAfter == 0)
  } // checks given Stones only
  override def checkWinWithNumber(
      field: FieldInterface,
      player: Stone
  ): (Boolean, Int, List[(Int, List[(Int, Int)])]) = {
    countedCalls = countedCalls + 1;
    (
      (countedCalls % alertAfter == 0),
      countedCalls % 4,
      List((0, List((0, 0))))
    )
  } // returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
}
