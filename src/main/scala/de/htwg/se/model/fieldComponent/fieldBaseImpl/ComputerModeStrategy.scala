package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.ModeStrategy
import de.htwg.se.util.PlayerState
import de.htwg.se.model.fieldComponent.FieldInterface

case class ComputerModeStrategy() extends ModeStrategy {
  override def put(x: Int, y: Int, field: Field): Field = {
    var field2 = field.player.put(x, y, field)
    if (y + 1 <= field.size2 - 1)
      field2.player.put(x, y + 1, field2)
    else
      field2
  }
}