// Interface to be implemented into Game.scala
package de.htwg.se.model.fieldComponent

import de.htwg.se.model.fieldComponent.fieldElements.Game
import de.htwg.se.util.Stone

trait FieldInterface { //== GameInterface
  def sizeOfDimY: Int //ex size
  def sizeOfDimX: Int //ex size2
  def put(x: Int, y: Int): Game
  def get(x: Int, y: Int): Stone
  def set(x: Int, y: Int, filling: String): FieldInterface
  def getPlayerState(): Boolean
  def setPlayer(player: String): PlayerState
  def setDifficulty(d: Int): Unit
  def getMode(): String
  def setMode(str: String): GameModeInterface
  def undo(x: Int, y: Int): FieldInterface
}
