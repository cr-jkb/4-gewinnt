// Interface to be used in GUI, TUI and Main
package de.htwg.se.controller.controllerComponent

import de.htwg.se.model.fieldComponent.{FieldInterface, GameModeInterface}
import de.htwg.se.util.Stone
import util.Observable

trait ControllerInterface extends Observable {
  def setGameState(won: Boolean): Unit
  def gameState: FieldInterface
  def CommandFeedbackPipe: String
  def newGame: Unit
  def put(x: Int, y: Int): Unit
  def undo: Unit
  def redo: Unit
  def setMode(str: String): GameModeInterface
  def setStrength(d: Int): Unit
  def getPlayer: Stone
  def quit: Unit
  def save: Unit
  def load: Unit
  def toString: String
}
