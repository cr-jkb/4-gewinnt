package de.htwg.se.fileIOComponent.fileIOJsonImpl

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.*
import scala.util.Success
import scala.concurrent.ExecutionContextExecutor
import scala.util.Failure

object fileIOAPI {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Any] =
      ActorSystem(Behaviors.empty, "fileIO")

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor =
      system.executionContext

    val route = path("fileio" / "load") {

      complete(
        HttpEntity(ContentTypes.`application/json`, fileIOJsonImpl.load())
      )
    } ~ path("fileio" / "save") {
      post {
        entity(as[String]) { game =>
          fileIOJsonImpl.save(game)
          complete("Game is saved!")
        }

      }

    }

    val server = Http().newServerAt("localhost", 8080).bind(route)

    server.onComplete {
      case Success(binding) =>
        println(
          s"Server now online. Please navigate to http://localhost:8080/fileio\nPress RETURN to stop..."
        )
      case Failure(exception) =>
        println(s"FileIO Rest service couldnt be started!Error: ${exception}")

    }
  }

}
