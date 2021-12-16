package util

import model.fieldComponent.fieldBaseImpl.Field

trait PlayerState {
  def put(x: Int, y: Int, field: Field): Field
}