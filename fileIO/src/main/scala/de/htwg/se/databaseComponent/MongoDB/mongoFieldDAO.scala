package de.htwg.se.databaseComponent

import de.htwg.se.databaseComponent.DAOInterface
import org.mongodb.scala._
import org.mongodb.scala.model._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.UpdateOptions
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

import java.util.Random

object mongoFieldDAO extends DAOInterface {
  var counter = 1

  val client: MongoClient = MongoClient("mongodb://localhost:27017")
  val database: MongoDatabase = client.getDatabase("mydb")
  val collection: MongoCollection[Document] =
    database.getCollection("savegames")

  def create(jsonField: String): Int = {
    // val rand: Random = new Random()
    // val id = rand.nextInt(900000)
    val id = counter
    val document: Document = Document("_id" -> id, "field" -> jsonField)
    val insertObservable: Observable[InsertOneResult] =
      collection.insertOne(document)

    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit =
        println(s"Inserted: $result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Insertion completeed")

    })
    counter += 1
    id

  }

  def read(id: Int): String = {
    val filter = equal("_id", id)

    val findObservable = collection.find(filter).first()
    val result: Document =
      Await.result(findObservable.toFuture(), 5.seconds)
    
    result.getString("field")

  }

  override def update(id: Int, jsonField: String): Unit = {
    val updateObservable: Observable[UpdateResult] =
      collection.updateOne(equal("_id", id), set("field", jsonField))

    updateObservable.subscribe(new Observer[UpdateResult] {
      override def onNext(result: UpdateResult): Unit =
        println(s"Updated: $result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Update completed")

    })
  }

  override def delete(id: Int): Unit = {
    val deleteObservable: Observable[DeleteResult] =
      collection.deleteOne(equal("_id", id))

    deleteObservable.subscribe(new Observer[DeleteResult] {
      override def onNext(result: DeleteResult): Unit =
        println(s"Deleted: $result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Deletion completed")

    })
  }

}
