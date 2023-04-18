// Interface for tracking Stone Symbols (whose turn it is) - will be used in model/fieldComponent/______PlayerState.scala {2}
package de.htwg.se
package tools

import fieldComponent.fieldBaseImpl.Field
import fieldComponent.fieldBaseImpl.ErrorField

trait PlayerState {
  def put(x: Int, y: Int, field: Field): ErrorField
}
