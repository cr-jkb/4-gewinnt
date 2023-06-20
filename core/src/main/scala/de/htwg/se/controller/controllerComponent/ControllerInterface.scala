// Interface to be used in GUI, TUI and Main
package de.htwg.se
package controller.controllerComponent

import de.htwg.se.util.Observable
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.util.GameMode
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone

trait ControllerInterface extends Observable {
  def field: FieldInterface
  def error: String
  def newField: Unit
  def put(x: Int, y: Int): Unit
  def undo: Unit
  def redo: Unit
  def setMode(str: String): GameMode
  def getMode(): String
  def setStrength(d: Int): Unit
  def getPlayer: Stone
  def quit: Unit
  def save: Unit
  def load: Unit
  def toString: String
  def getWinner(): String
}
