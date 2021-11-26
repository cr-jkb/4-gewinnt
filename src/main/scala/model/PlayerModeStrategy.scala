package model

import util.ModeStrategy
import util.PlayerState

case class PlayerModeStrategy() extends ModeStrategy {
  override def put(x: Int, y: Int, field: Field): Field = {
    field.player.put(x, y, field)
  }
}