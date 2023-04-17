// Implemented 1-Instance Class - Read FileName as PlayersTurn = true / false

package de.htwg.se.model.fieldComponent.gameState

import de.htwg.se.model.fieldComponent.PlayerState
import de.htwg.se.model.fieldComponent.fieldElements.Game
import de.htwg.se.util.Stone

case class TruePlayerState() extends PlayerState {
  override def put(
      x: Int,
      y: Int,
      field: Game
  ): (Game, Error) = { // to be called from field.player in Game.scala and ___ModeStrategy.scala{2}
    if (field.get(x, y) == Stone.Empty)
      field.player =
        FalsePlayerState() // hand ball over to the other player (to 'O')
      (
        (field.copy(field.field.replaceCell(x, y, Stone.X)), "")
        /* Error("") */
      ) // x is vertical
    else (field, "Game already taken")
  }
}
