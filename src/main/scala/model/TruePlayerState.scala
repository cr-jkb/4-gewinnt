package model

import util.PlayerState
import model.Stone

case class TruePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Field): Field = {
    field.player = FalsePlayerState()
    field.copy(field.matrix.replaceCell(x, y, Stone.X))
  }
}