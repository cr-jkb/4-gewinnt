package de.htwg.se.util

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field

trait PlayerState {
  def put(x: Int, y: Int, field: Field): Field
}