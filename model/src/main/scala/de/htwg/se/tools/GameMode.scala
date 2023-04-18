//Interface for Game Mode Setting - will be used in Swap-In Classes @ /model/fieldComponent/______ModeStrategy.scala {2}
package de.htwg.se
package tools
import fieldComponent.fieldBaseImpl.Field
import fieldComponent.fieldBaseImpl.ErrorField

trait GameMode {
  def put(x: Int, y: Int, field: Field): ErrorField

  def setDifficulty(diff: Int): Unit
}
