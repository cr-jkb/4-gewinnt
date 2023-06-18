//Interface for Game Mode Setting - will be used in Swap-In Classes @ /model/fieldComponent/______ModeStrategy.scala {2}
package de.htwg.se.util
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.ErrorField

trait GameMode {
  def put(x: Int, y: Int, field: Field): ErrorField

  def setDifficulty(diff: Int): Unit
  def getDifficulty(): Int
}
