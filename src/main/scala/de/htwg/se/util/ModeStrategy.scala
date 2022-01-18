//Interface for Game Mode Setting - will be used in Swap-In Classes @ /model/fieldComponent/______ModeStrategy.scala {2}
package de.htwg.se.util

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field

trait ModeStrategy {
  def put(x: Int, y: Int, field: Field): Field
}