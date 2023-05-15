package de.htwg.se.databaseComponent
import slick.jdbc.PostgresProfile.api.*
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

class GameDAO extends DAOInterface {
  val ip = "localhost"
  val port = "5432"
  val name = "fileio"
  val db = Database.forURL(
    url = "jdbc:postgresql://" + ip + ":" + port + "/" + name,
    user = "username",
    password = "password",
    driver = "org.postgresql.Driver"
  )
  val fieldTable = TableQuery(new FieldTable(_))

  override def create: Unit = {
    val running = Future(
      Await.result(
        db.run(
          DBIO.seq(
            fieldTable.schema.create
          )
        ),
        Duration.Inf
      )
    )
    running.onComplete {
      case Success(_) =>
        println("Connection to DB & Creation of Tables successful!")
      case Failure(e) => println("Error: " + e)
    }
  }
  override def read: Unit = {}
  override def update: Unit = {}
  override def delete: Unit = {}

}
