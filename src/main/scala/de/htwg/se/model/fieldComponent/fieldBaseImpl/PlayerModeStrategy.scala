package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.ModeStrategy
import de.htwg.se.model.fieldComponent.FieldInterface

case class PlayerModeStrategy() extends ModeStrategy {
  override def put(x: Int, y: Int, field: Field): Field = {
    field.player.put(x, y, field) // hand parameters through to the next layer (which is player)
  }
}