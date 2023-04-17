// Implemented 1-Instance Class - Read FileName as PlayersTurn = true / false

package de.htwg.se.model.fieldComponent.gameState

import de.htwg.se.model.fieldComponent.PlayerState
import de.htwg.se.model.fieldComponent.fieldElements.Game
import de.htwg.se.util.Stone

case class FalsePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Game): (Game, Error) = { // to be called from field.scala
    if (field.get(x,y) == Stone.Empty)
      field.player = TruePlayerState() // hand ball over to the other player (to 'X')
      (field.copy(field.field.replaceCell(x, y, Stone.O)), "None")
    else
      (field, "Game already taken")
  }
}