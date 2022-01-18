// Interface to be implemented into Field.scala
package de.htwg.se.model.fieldComponent

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.util.ModeStrategy
trait FieldInterface {
  def size: Int
  def size2: Int
  def put(x: Int, y: Int): Field
  def get(x: Int, y: Int): Stone
  def getPlayerState(): Boolean
  def setMode(str: String): ModeStrategy
  def undo(x: Int, y: Int): FieldInterface
}