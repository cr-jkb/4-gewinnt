package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.PlayerState

case class FalsePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Field): Field = {
    field.player = TruePlayerState()
    field.copy(field.matrix.replaceCell(x, y, Stone.O))
  }
}