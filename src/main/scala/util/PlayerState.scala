package util

import model.Field

trait PlayerState {
  def put(x: Int, y: Int, field: Field): Field
}