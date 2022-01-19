package de.htwg.se.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.util.Observable
import de.htwg.se.util.Command
import de.htwg.se.util.UndoManager
import de.htwg.se.util.ModeStrategy

case class Controller (var field: FieldInterface) extends ControllerInterface with Observable:

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