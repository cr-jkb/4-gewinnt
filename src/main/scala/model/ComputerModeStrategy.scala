package model

import util.ModeStrategy
import util.PlayerState

case class ComputerModeStrategy() extends ModeStrategy {
  override def put(x: Int, y: Int, field: Field): Field = {
    var field2 = field.player.put(x, y, field)
    if (y + 1 <= field.size2 - 1)
      field2.player.put(x, y + 1, field2)
    else
      field2
  }
}