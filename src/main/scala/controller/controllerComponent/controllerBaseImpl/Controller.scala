package controller.controllerComponent.controllerBaseImpl

import model.fieldComponent.fieldBaseImpl.Field
import model.fieldComponent.FieldInterface
import controller.controllerComponent.ControllerInterface
import util.Observable
import util.ModeStrategy
import util.Command
import util.UndoManager
import scala.languageFeature.existentials

case class Controller(var field: FieldInterface) extends ControllerInterface with Observable:
  val undoManager = new UndoManager[FieldInterface]
  def newField =
    field = new Field()
    notifyObservers
  def put(x: Int, y: Int) =
    field = undoManager.doStep(field, new PutCommand(x, y, this))
    notifyObservers
  def undo = 
    field = undoManager.undoStep(field)
    notifyObservers
  def redo = 
    field = undoManager.redoStep(field)
    notifyObservers
  def setMode(str: String): ModeStrategy = field.setMode(str)
  def quit = 
    killObservers
  override def toString = field.toString