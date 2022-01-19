// Implemented 1-Instance Class - Read FileName as PlayersTurn = true / false

package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.PlayerState

case class FalsePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Field): Field = { // to be called from field.scala
    field.player = TruePlayerState() // hand ball over to the other player (to 'X')
    field.copy(field.matrix.replaceCell(x, y, Stone.O))
  }
}