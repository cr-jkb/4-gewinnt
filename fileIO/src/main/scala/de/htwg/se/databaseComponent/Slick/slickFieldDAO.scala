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
import play.api.libs.json

object slickFieldDAO extends DAOInterface {
  val ip = "localhost"
  val port = "5432"
  val dbname = "fileio"
  val db = Database.forURL(
    url =
      "jdbc:postgresql://" + ip + ":" + port + "/" + dbname + "?serverTimezone=UTC",
    user = sys.env.getOrElse("POSTGRES_USER", "username").toString,
    password = sys.env.getOrElse("POSTGRES_USER", "password").toString,
    driver = "org.postgresql.Driver"
  )
  val fieldTable = TableQuery(new FieldTable(_))

  // the queries always return the number of rows affected

  def initiate(): Unit = {
    val query = fieldTable.schema.createIfNotExists
    val result = db.run(query)

    result.onComplete {
      case Success(_) =>
        println("Connection to DB & Creation of FieldTable successful!")
      case Failure(e) => println("Error: " + e)
    }
    Await.result(result, Duration.Inf)

  }

  override def create(jsonField: String): Int = {
    val rand: Random = new Random();
    val id = rand.nextInt(900000);
    val query = fieldTable += (id, jsonField)

    val result = db.run(query)
    result.onComplete {
      case Success(rowsCreated) =>
        println(s"Created $rowsCreated entry with id= $id"); id;
      case Failure(exc) =>
        println(s"error on create: ${exc.getMessage}"); -1;
    }
    Await.result(result, Duration.Inf)
  }

  override def read(id: Int): String = {
    val query =
      fieldTable.filter(_.id === id).map(_.jsonField).result.headOption
    val result = db.run(query)

    result.onComplete {
      case Success(Some(jsonField)) =>
        println(s"Entry with id= $id found."); jsonField.toString
      case Success(None) =>
        throw new NoSuchElementException(s"No record found for id=$id"); ""
      case Failure(exc) => println(s"error on getting entry with id= $id")
    }
    Await.result(result, atMost = 10.seconds).getOrElse("")

  }

  override def update(id: Int, jsonField: String): Unit = {
    val query =
      fieldTable.filter(_.id === id).map(_.jsonField).update(jsonField)
    val result = db.run(query)

    result.onComplete {
      case Success(rowsUpdated) =>
        println(s"Updated $rowsUpdated rows with id: $id")
      case Failure(exc) => println(s"error on delete: ${exc.getMessage}")
    }
    Await.result(result, Duration.Inf)
  }

  override def delete(id: Int): Unit = {
    val query =
      fieldTable.filter(_.id === id).delete
    val result = db.run(query)

    result.onComplete {
      case Success(rowsDeleted) => println(s"Deleted $rowsDeleted records")
      case Failure(exc) => println(s"error on delete: ${exc.getMessage}")
    }
    Await.result(result, Duration.Inf)
  }

}
