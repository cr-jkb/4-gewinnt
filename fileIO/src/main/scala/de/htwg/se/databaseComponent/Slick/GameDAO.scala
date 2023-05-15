package de.htwg.se.databaseComponent
import slick.jdbc.PostgresProfile.api.*
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}
import java.util.Random;

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

  override def create(jsonField: String): Int = {
    Random rand = new Random();
    Int id = rand.nextInt(900000);
    fieldTable += (id, jsonField);

  }
  override def read(id: Int): String = {
    val query = fieldTable.filter(_.id === id).result.headOption
    val result = Await.result(db.run(query), Duration.Inf)
    result match {
      case Some(row) => row.jsonField
      case None =>
        throw new NoSuchElementException(s"No record found for id=$id")
    }

  }

  override def update(id: Int, jsonField: String): Unit = {
    val query =
      fieldTable.filter(_.id === id).map(_.jsonField).update(jsonField)
    val result = Await.result(db.run(query), Duration.Inf)
  }

  override def delete(id: Int): Unit = {
    val query = fieldTable.filter(_.id === id).result.headOption
    query.delete();
  }

}
