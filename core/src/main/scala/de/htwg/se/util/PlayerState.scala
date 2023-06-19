// Interface for tracking Stone Symbols (whose turn it is) - will be used in model/fieldComponent/______PlayerState.scala {2}
package de.htwg.se.util

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
/* import de.htwg.se.model.fieldComponent.fieldBaseImpl.ErrorField */

trait PlayerState {
  def put(x: Int, field: Field): Field
}
