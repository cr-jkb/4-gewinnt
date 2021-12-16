package controller.controllerComponent

import util.ModeStrategy
import model.fieldComponent.FieldInterface
import util.Observable

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