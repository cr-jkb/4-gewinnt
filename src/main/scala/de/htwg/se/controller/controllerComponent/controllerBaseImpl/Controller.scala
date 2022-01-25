package de.htwg.se.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.util.Observable
import de.htwg.se.util.Command
import de.htwg.se.util.UndoManager
import de.htwg.se.util.ModeStrategy
object SpaceFound extends Exception
object FullRow extends Exception


case class Controller (var field: FieldInterface) extends ControllerInterface with Observable:

  val undoManager = new UndoManager[FieldInterface]
  
  def newField =
    field = new Field() //no need for new if "instance-ized" with parameters
    notifyObservers

  def loadField =
    field = field.meshFromXml("startUp.xml") //convert to Dependency Injection
    notifyObservers
  
  def put(x: Int, y: Int) = //Here put instead of x low_x in the PutCommand

    var low_x = field.size2-1
    try {
      for (low_x <- field.size2-1 until 0) { //gehe von unten los !!!!!JAVA GIBT EINEN FICK AUF DIESE LOOP
        print(s"(${low_x}/${field.size2})#${field.get(low_x, y).toString}#")
        if (field.get(low_x, y) == Stone.Empty) throw SpaceFound; //never triggeres but is correct
      }
      throw FullRow
    } catch {
    case SpaceFound => print(s"will put to ${low_x+1}")
    case FullRow => print(s"the vertical row at ${y+1} is full")
    }

    field = undoManager.doStep(field, new PutCommand(x, y, this))
    //redoManager should be cleared here
    
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