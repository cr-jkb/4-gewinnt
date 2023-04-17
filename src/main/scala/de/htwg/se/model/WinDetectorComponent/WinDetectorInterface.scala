package de.htwg.se.model.WinDetectorComponent

import de.htwg.se.model.fieldComponent.{FieldInterface, PlayerState}
import de.htwg.se.util.Stone
import java.lang.reflect.Field

trait WinDetectorInterface {
  def checkWin(field: FieldInterface): (Boolean, Stone) //checks the whole Game

  def checkWinFor( //checks given Stones only
      field: FieldInterface,
      player: Stone
  ): (Boolean)

  def checkWinWithNumber( //returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
      field: FieldInterface,
      player: Stone
  ): (Boolean, Int, List[(Int, List[(Int, Int)])])
}
