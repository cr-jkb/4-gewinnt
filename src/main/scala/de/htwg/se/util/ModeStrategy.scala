package de.htwg.se.util

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field

trait ModeStrategy {
  def put(x: Int, y: Int, field: Field): Field
}