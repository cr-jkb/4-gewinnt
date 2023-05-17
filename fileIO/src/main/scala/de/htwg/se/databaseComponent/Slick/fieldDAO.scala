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
    url = "jdbc:postgresql://" + ip + ":" + port + "/" + name + "?serverTimezone=UTC",
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

  def create(jsonField: String): Int = {
    val rand : Random = new Random();
    val id = rand.nextInt(900000);
    fieldTable += (id, jsonField);
    val query = DBIO.seq(
      fieldTable.schema.createIfNotExists,
    )
    

    var result = Future(Await.result(db.run(query)), Duration.Inf)

    result.onComplete {
      case Success(obj) => println(s"Del $obj with $id")
      case Failure(exc) => println(s"error on delete: ${exc.getMessage}")
    }
  }

  override def read(id: Int): String = {
    val query = fieldTable.filter(_.id === id).result.headOption
    val result = Await.result(db.run(query), Duration.Inf)
    result match {
      case Some(row) => row
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
    val query = fieldTable.filter(_.id === id).result.headOption.delete()
    val result = db.run(query)

    result.onComplete {
      case Success(obj) => println(s"Del $obj")
      case Failure(exc) => println(s"error on delete: ${exc.getMessage}")
    }
  }

}
