package controller

import model.Field
import model.Stone
import util.Observable
import util.ModeStrategy
import util.Command
import util.UndoManager
import scala.languageFeature.existentials

case class Controller(var field: Field) extends Observable:
  val undoManager = new UndoManager[Field]
  def this() = this(new Field())
  def newField =
    field = new Field()
    notifyObservers
  def put(x: Int, y: Int) =
    field = undoManager.doStep(field, PutCommand(x, y))
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