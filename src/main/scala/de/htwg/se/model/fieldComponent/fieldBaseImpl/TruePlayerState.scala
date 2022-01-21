// Implemented 1-Instance Class - Read FileName as PlayersTurn = true / false

package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.PlayerState

case class TruePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Field): Field = { // to be called from field.player in Field.scala and ___ModeStrategy.scala{2}
  if (field.get(x, y) == Stone.Empty)
    field.player = FalsePlayerState() // hand ball over to the other player (to 'O')
    field.copy(field.matrix.replaceCell(x, y, Stone.X))
  else
      field
  }
}