package util

import model.Field

trait ModeStrategy {
  def put(x: Int, y: Int, field: Field): Field
}