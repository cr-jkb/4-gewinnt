package model.fieldComponent.fieldBaseImpl

import util.PlayerState

case class TruePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Field): Field = {
    field.player = FalsePlayerState()
    field.copy(field.matrix.replaceCell(x, y, Stone.X))
  }
}