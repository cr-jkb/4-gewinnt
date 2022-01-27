// Interface to be implemented into Field.scala
package de.htwg.se.model.fieldComponent

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.ErrorField
import de.htwg.se.util.ModeStrategy
import de.htwg.se.util.PlayerState

trait FieldInterface {
  def size: Int
  def size2: Int
  def error: String
  def put(x: Int, y: Int): Field
  def get(x: Int, y: Int): Stone
  def set(x: Int, y: Int, filling: String): FieldInterface
  def getPlayerState(): Boolean
  def setPlayer(player: String): PlayerState
  def getMode(): String
  def setMode(str: String): ModeStrategy
  def undo(x: Int, y: Int): FieldInterface
}