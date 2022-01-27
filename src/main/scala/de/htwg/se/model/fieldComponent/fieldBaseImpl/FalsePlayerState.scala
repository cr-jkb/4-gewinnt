// Implemented 1-Instance Class - Read FileName as PlayersTurn = true / false

package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.PlayerState

case class FalsePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Field): ErrorField = { // to be called from field.scala
    if (field.get(x,y) == Stone.Empty)
      field.player = TruePlayerState() // hand ball over to the other player (to 'X')
      new ErrorField(field.copy(field.matrix.replaceCell(x, y, Stone.O)), "None")
    else
      new ErrorField(field, "Field already taken")
  }
}