package de.htwg.se.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.model.fieldComponent.{FieldInterface, GameModeInterface}
import de.htwg.se.controller.controllerComponent.ControllerInterface
import com.google.inject.Guice
import de.htwg.se.MainModule
import de.htwg.se.model.fileIOComponent.FileIOInterface
import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.model.fieldComponent.fieldElements.Game
import de.htwg.se.util.Stone
import util.{Command, Observable, UndoManager}

object SpaceFound extends Exception
object FullRow extends Exception
import scala.util.{Try, Success, Failure}

case class Controller @Inject() (@Named("DefField") var gameState: FieldInterface)
    extends ControllerInterface
    with Observable:
  var gameWon = false
  var CommandFeedbackPipe = ""

  def newGame =
    gameWon = false
    gameState = new Game() // no need for new if "instance-ized" with parameters
    notifyObservers

  private val undoManager = new UndoManager[FieldInterface]
  private val injector = Guice.createInjector(new MainModule)
  private val fileIo = injector.getInstance(classOf[FileIOInterface])

  def put(x: Int) =


  def put(x: Int, y: Int) = //TODO23 REFACTOR: Enthält schon 4 Gewinnt Spiel Logik: sollte allgemeines put erlauben
    var low_x = gameState.sizeOfDimY - 1
    val result = Try {
      /* .find() */
      for (try_x <- gameState.sizeOfDimY - 1 to 0 by -1) {
        if (gameState.get(try_x, y) == Stone.Empty) {
          low_x = try_x
          throw SpaceFound
        }
      }
      throw FullRow
    } match {
      case Success(low_x) => low_x
      case Failure(e) =>
        e match {
          case SpaceFound =>
            undoManager.clearRedo();
            gameState = undoManager.doStep(gameState, new PutCommand(low_x, y, this));
            CommandFeedbackPipe = "Stone successfully set"
          case FullRow => CommandFeedbackPipe = (s"the vertical row at ${y + 1} is full\n")
        }
    }
    notifyObservers

  def undo =
    val oldfield = gameState
    gameState = undoManager.undoStep(gameState)
    if (gameState == oldfield)
      CommandFeedbackPipe = "Undo fehler!"
    else
      CommandFeedbackPipe = "Undo erfolgreich"
    notifyObservers

  def redo =
    val oldfield = gameState
    gameState = undoManager.redoStep(gameState)
    if (gameState == oldfield)
      CommandFeedbackPipe = "Redo fehler!"
    else
      CommandFeedbackPipe = "Redo erfolgreich"
    notifyObservers

  override def setGameState(won: Boolean) = { gameWon = won }
  def setMode(str: String) = { GameModeInterface = gameState.setMode(str) }//TODO23 success?
  def getPlayer: Stone = if (gameState.getPlayerState()) Stone.X else Stone.O
  def setStrength(d: Int) = { gameState.setDifficulty(d) }

  def quit =
    killObservers

  def save: Unit =
    fileIo.save(gameState)
    notifyObservers

  def load: Unit =
    gameState = fileIo.load
    notifyObservers

  override def toString = gameState.toString
