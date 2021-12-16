package util

import model.fieldComponent.fieldBaseImpl.Field

trait ModeStrategy {
  def put(x: Int, y: Int, field: Field): Field
}