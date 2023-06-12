package de.htwg.se.fileIOComponent.fileIOJsonImpl

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.*
import scala.util.Success
import scala.concurrent.ExecutionContextExecutor
import scala.util.Failure
import java.time._
import de.htwg.se.databaseComponent.MongodbImpl
import de.htwg.se.databaseComponent.SlickImpl
import de.htwg.se.databaseComponent.slickFieldDAO

object fileIOAPI {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Any] =
      ActorSystem(Behaviors.empty, "fileIO")

    slickFieldDAO.initiate()

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor =
      system.executionContext

    val route = path("fileio" / "load") {
      complete(
        /* fileIOJsonImpl.load() */
        HttpEntity(
          ContentTypes.`application/json`,
          //fileIOJsonImpl.load()
          // SlickImpl.load()
          MongodbImpl.load()
        )
      )
    } ~ path("fileio" / "save") {
      post {
        entity(as[String]) { game =>
          //fileIOJsonImpl.save(game)
          /* SlickImpl.save(game) */
          MongodbImpl.save(game)

          complete("Game is saved!")
        }

      }

    }

    val server = Http().newServerAt("0.0.0.0", 8080).bind(route)

    server.onComplete {
      case Success(binding) =>
        println(
          s"Server now online. Please navigate to http://localhost:8080/fileio\nPress RETURN to stop..."
        )
      /*var quit = false
        while (!quit) {
          /* quit = true; */
          Thread.sleep(1000L);
        }*/
      case Failure(exception) =>
        println(s"FileIO Rest service couldnt be started!Error: ${exception}")

    }
    while (true) {
      scala.io.StdIn.readLine() // blocks the main thread until user input
      Thread.sleep(1000L);
    }

    server
      .flatMap(_.unbind()) // unbind the server binding
      .onComplete(_ => system.terminate()) // terminate the actor system
  }

}
