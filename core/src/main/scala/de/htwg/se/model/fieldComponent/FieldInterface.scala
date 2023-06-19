// Interface to be implemented into Field.scala
package de.htwg.se.model
package fieldComponent
import de.htwg.se.util.GameMode
import de.htwg.se.util.PlayerState
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone

trait FieldInterface {
  def sizeOfDimY: Int // ex size
  def sizeOfDimX: Int // ex size2
  def getLatestError: String
  def setLatestError(err: String): Unit
  def put(x: Int): Field
  def get(x: Int, y: Int): Stone
  def set(x: Int, y: Int, filling: String): Field
  def getPlayerState(): Boolean
  def setPlayer(player: String): PlayerState
  def getMode(): String
  def setMode(str: String): GameMode
  def setDifficulty(d: Int): Unit
  def getDifficulty(): Int
  def undo(x: Int, y: Int): FieldInterface
  def setWinner(winner: Stone): Unit
  def getWinner(): Stone
}
