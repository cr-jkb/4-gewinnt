package de.htwg.se.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.util.Observable
import de.htwg.se.util.Command
import de.htwg.se.util.UndoManager
import de.htwg.se.util.ModeStrategy
import com.google.inject.Guice
import de.htwg.se.MainModule
import de.htwg.se.model.fileIOComponent.FileIOInterface
import com.google.inject.Inject
import com.google.inject.name.Named

case class Controller @Inject()(@Named("DefField") var field: FieldInterface) extends ControllerInterface with Observable:

  val undoManager = new UndoManager[FieldInterface]
  val injector = Guice.createInjector(new MainModule)
  val fileIo = injector.getInstance(classOf[FileIOInterface])

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

  def save: Unit =
    fileIo.save(field)
    notifyObservers

  def load: Unit =
    field = fileIo.load
    notifyObservers

  override def toString = field.toString