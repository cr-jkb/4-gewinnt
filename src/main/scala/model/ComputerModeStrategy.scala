package model

import util.ModeStrategy
import util.PlayerState

case class ComputerModeStrategy() extends ModeStrategy {
  override def put(x: Int, y: Int, field: Field): Field = {
    var field2 = field.player.put(x, y, field)
    if (y + 1 <= field.size2)
      field2.player.put(x, y + 1, field2)
    else if (y - 1 >= 1)
      field2.player.put(x, y - 1, field2)
    else if (x - 1 >= 1)
      field2.player.put(x - 1, y, field2)
    else
      println("ERROR!!")
      field2
  }
}