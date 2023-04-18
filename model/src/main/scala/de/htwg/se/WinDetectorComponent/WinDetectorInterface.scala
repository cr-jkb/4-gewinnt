package de.htwg.se
package WinDetectorComponent
import de.htwg.se.fieldComponent.FieldInterface
import de.htwg.se.tools.PlayerState
import de.htwg.se.fieldComponent.fieldBaseImpl.Stone
import java.lang.reflect.Field

trait WinDetectorInterface {
  def checkWin(
      field: FieldInterface
  ): (Boolean, Stone) // checks the whole Field

  def checkWinFor( // checks given Stones only
      field: FieldInterface,
      player: Stone
  ): (Boolean)

  def checkWinWithNumber( // returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
      field: FieldInterface,
      player: Stone
  ): (Boolean, Int, List[(Int, List[(Int, Int)])])
}
