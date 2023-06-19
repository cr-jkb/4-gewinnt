package de.htwg.se
package controller.controllerComponent.controllerBaseImpl
import com.google.inject.Guice

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.util.Observable
import de.htwg.se.util.UndoManager
import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.util.Command
import de.htwg.se.util.GameMode
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.Http
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success
import de.htwg.se.util.Util

object SpaceFound extends Exception
object FullRow extends Exception
import scala.util.{Try, Success, Failure}

case class Controller @Inject() (@Named("DefField") var field: FieldInterface)
    extends ControllerInterface
    with Observable:

  val fileIOServer = "http://localhost:8080/fileio"

  val undoManager = new UndoManager[FieldInterface]
  var error = ""

  def getWinner(): String = { field.getWinner().toString() }

  def newField =
    field = new Field() // no need for new if "instance-ized" with parameters
    notifyObservers

  def put(x: Int, y: Int) =
    var low_x = field.sizeOfDimY - 1
    val result = Try {
      for (try_x <- field.sizeOfDimY - 1 to 0 by -1) {
        if (field.get(try_x, y) == Stone.Empty) {
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
            field = undoManager.doStep(field, new PutCommand(low_x, y, this));
            error = "Stone successfully set"
          case FullRow => error = (s"the vertical row at ${y + 1} is full\n")
        }
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

  def setMode(str: String): GameMode = field.setMode(str);
  def getPlayer: Stone = if (field.getPlayerState()) Stone.X else Stone.O
  def setStrength(d: Int) = field.setDifficulty(d)
  def getStrength(): Int = field.getDifficulty()

  def quit =
    killObservers

  def save: Unit =
    // fileIo.save(field)
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    println("Accessing the API - Please wait (10s Timeout)")
    val response: Future[HttpResponse] = Http().singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = fileIOServer + "/save",
        entity = Util.toJsonString(field)
      )
    )
    response.onComplete {
      case Failure(e) =>
        println(s"Fehler beim Speichern: \n$e");
        sys.error("Fail when saving field.");
      case Success(value) =>
        println("Spiel ueber API gespeichert.")
        notifyObservers

    }

  def load: Unit =
    // field = fileIo.load
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext
    println("Accessing the API - Please wait (10s Timeout)")
    val response: Future[HttpResponse] = Http().singleRequest(
      HttpRequest(
        uri = fileIOServer + "/load"
      )
    )
    response.onComplete {
      case Failure(e) =>
        println(s"Fehler beim Lesen: \n$e");
        sys.error("Fail when loading field.");
      case Success(value) =>
        Unmarshal(value.entity).to[String].onComplete {
          case Failure(e) =>
            println(s"Fehler beim Lesen: \n$e");
            sys.error("Failed unmarshalling");
          case Success(value) =>
            println("Spiel ueber API geladen.")
            field = Util.jsonToField(value)
            notifyObservers
        }
    }

  override def toString = field.toString
