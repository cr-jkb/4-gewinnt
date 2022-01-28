package de.htwg.se.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.fieldBaseImpl.ErrorField
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
object SpaceFound extends Exception
object FullRow extends Exception

case class Controller @Inject()(@Named("DefField") var field: FieldInterface) extends ControllerInterface with Observable:

  val undoManager = new UndoManager[FieldInterface]
  val injector = Guice.createInjector(new MainModule)
  val fileIo = injector.getInstance(classOf[FileIOInterface])
  var error = ""
  
  def newField =
    field = new Field() //no need for new if "instance-ized" with parameters
    notifyObservers
  
  def put(x: Int, y: Int) =
    var low_x = field.size-1
    try {      
      for (try_x <- field.size-1 to 0 by -1) { //gehe von unten los
        if (field.get(try_x, y) == Stone.Empty) { low_x = try_x; throw SpaceFound; }
      }
      throw FullRow
    } catch {
    case SpaceFound => undoManager.clearRedo(); field = undoManager.doStep(field, new PutCommand(low_x, y, this)); error = "Stone successfully set"  //print(s"will put to ${low_x}\n")
    case FullRow => error = (s"the vertical row at ${y+1} is full\n") //throw noAction
    }    
    notifyObservers
  
  def undo = 
    val oldfield = field
    field = undoManager.undoStep(field)
    if (field == oldfield)
      error = "Undo fehler!"
    else
      error = "Undo erfolgreich"
    notifyObservers
  
  def redo = 
    val oldfield = field
    field = undoManager.redoStep(field)
    if (field == oldfield)
      error = "Redo fehler!"
    else
      error = "Redo erfolgreich"
    notifyObservers
  
  def setMode(str: String): ModeStrategy = field.setMode(str)
  def getPlayer: Stone = if(field.getPlayerState()) Stone.X else Stone.O
  def setStrength(d: Int) = field.setDifficulty(d)
 
  def quit = 
    killObservers

  def save: Unit =
    fileIo.save(field)
    notifyObservers

  def load: Unit =
    field = fileIo.load
    notifyObservers

  override def toString = field.toString