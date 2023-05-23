package de.htwg.se.databaseComponent

import de.htwg.se.databaseComponent.DAOInterface
import org.mongodb.scala._
import org.mongodb.scala.model._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.UpdateOptions
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}
import scala.concurrent.Await
import scala.concurrent.duration._

import java.util.Random

object mongoFieldDAO extends DAOInterface {

  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("mydb")
  val collection: MongoCollection[Document] =
    database.getCollection("savegames")

  def create(jsonField: String): Int = {
    val rand: Random = new Random()
    val id = rand.nextInt(900000)
    val document: Document = Document("_id" -> id, "field" -> jsonField)
    val insertObservable: Observable[Completed] = collection.insertOne(document)

    insertObservable.subscribe( new Observer[Completed]{
      override def onNext(result: Completed): Unit = println(s"Inserted: $result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Completeed")

    })
    id

  }

  def read(id: Int): String = {
    val filter = equal("_id", id)
    val findObservable = collection.find(filter)
    val resultField = ""

    findObservable.subscribe(new Observer[Document]{
      override def onNext(document : Document): Unit = 
        println(s"found: $result"); 
        val result = document.getString("field");
        resultField = result;

      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Completed")
    })
    resultField
    
 
  }
    

  override def update(id: Int, jsonField: String): Unit = {
    val updateAction = collection.updateOne(equal("_id", id), set("field", jsonField))
  }

  override def delete(id: Int): Unit = {
    val deleteAction = collection.deleteOne(equal("_id", id))
  }
    
}
