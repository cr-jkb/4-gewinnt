package de.htwg.se.controller.controllerComponent

import de.htwg.se.util.ModeStrategy
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.util.Observable

trait ControllerInterface extends Observable {
  def field: FieldInterface
  def newField: Unit
  def put(x: Int, y: Int): Unit
  def undo: Unit
  def redo: Unit
  def setMode(str: String): ModeStrategy
  def quit: Unit
  def toString: String
}