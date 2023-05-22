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

object fieldDAO extends DAOInterface {
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

  /*val running = Future(
    Await.result(
      db.run(
        DBIO.seq(
          fieldTable.schema.create
        )
      ),
      Duration.Inf
    )
  )*/
  /*running.onComplete {
    case Success(_) =>
      println("Connection to DB & Creation of Tables successful!")
    case Failure(e) => println("Error: " + e)
  }*/

  def create(jsonField: String): Int = {
    val rand: Random = new Random();
    val id = rand.nextInt(900000);
    /*fieldTable += (id, jsonField);*/
    val query = DBIO.seq(
      fieldTable.schema.createIfNotExists
    )
    val result = Future(Await.result(db.run(query), Duration.Inf))
    result.onComplete {
      case Success(obj) => println(s"Create $obj with $id"); return id;
      case Failure(exc) =>
        println(s"error on create: ${exc.getMessage}"); return -1;
    }
    return id;
  }

  override def read(id: Int): String = {
    val query = fieldTable.filter(_.id === id).result.headOption
    val result = Await.result(db.run(query), atMost = 10.seconds)
    /*Json.toJson()*/
    result match {
      case Some(row) => row.toString
      case None =>
        throw new NoSuchElementException(s"No record found for id=$id"); ""
    }

  }

  override def update(id: Int, jsonField: String): Unit = {
    val query =
      fieldTable.filter(_.id === id).map(_.jsonField).update(jsonField)
    val result = Await.result(db.run(query), Duration.Inf)
  }

  override def delete(id: Int): Unit = {
    val query =
      fieldTable.filter(_.id === id).result.headOption // .delete!?!?!?
    val result = db.run(query)

    result.onComplete {
      case Success(obj) => println(s"Del $obj")
      case Failure(exc) => println(s"error on delete: ${exc.getMessage}")
    }
  }

}
