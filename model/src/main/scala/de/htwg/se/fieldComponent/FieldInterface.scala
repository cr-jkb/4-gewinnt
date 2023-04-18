// Interface to be implemented into Field.scala
package de.htwg.se
package fieldComponent
import de.htwg.se.tools.GameMode
import de.htwg.se.tools.PlayerState
import de.htwg.se.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.fieldComponent.fieldBaseImpl.Stone

trait FieldInterface {
  def sizeOfDimY: Int // ex size
  def sizeOfDimX: Int // ex size2
  def error: String
  def put(x: Int, y: Int): Field
  def get(x: Int, y: Int): Stone
  def set(x: Int, y: Int, filling: String): FieldInterface
  def getPlayerState(): Boolean
  def setPlayer(player: String): PlayerState
  def getMode(): String
  def setMode(str: String): GameMode
  def setDifficulty(d: Int): Unit
  def undo(x: Int, y: Int): FieldInterface
}
