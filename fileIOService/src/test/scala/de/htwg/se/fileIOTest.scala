import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

import de.htwg.se.fileIOComponent.fileIOJsonImpl.fileIOJsonImpl
import de.htwg.se.databaseComponent.MongodbImpl
import de.htwg.se.databaseComponent.SlickImpl
import de.htwg.se.databaseComponent.mongoFieldDAO
import de.htwg.se.databaseComponent.slickFieldDAO


class fileIOTest extends AnyWordSpec {
  val testField : String = "{\"field\":{\"mode\":\"multiplayer\",\"difficulty\":-1,\"player\":\"false\",\"cells\":[{\"row\":0,\"col\":0,\"value\":\" X\"}]}}"
  

  "FileIO" should {
    
    "write to and read from MongoDB" in {
      MongodbImpl.save(testField)
      MongodbImpl.load() should be(
        testField
      )
    }
    "write to and read from Slick" in {
      SlickImpl.save(testField)
      SlickImpl.load() should be(
        testField
      )
    }
    "write to and read from JSON File" in {
      fileIOJsonImpl.save(testField)
      fileIOJsonImpl.load() should be(
        testField
      )
    }
  }
  "MongoFieldDAO" should {
    
    "create, read, update, and delete field" in {
      val id = mongoFieldDAO.create(testField)
      mongoFieldDAO.read(id) should be(testField)
      print(id)

      val updatedField: String = "{\"field\":{\"mode\":\"singleplayer\",\"difficulty\":0,\"player\":\"true\",\"cells\":[{\"row\":0,\"col\":0,\"value\":\" X\"}]}}"
      mongoFieldDAO.update(id, updatedField)
      mongoFieldDAO.read(id) should be(updatedField)

      mongoFieldDAO.delete(id)
    }
  
  }
  "SlickFieldDAO" should {
    
    "create, read, update, and delete field" in {
      val id = slickFieldDAO.create(testField)
      slickFieldDAO.read(id) should be(testField)
      print(id)

      val updatedField: String = "{\"field\":{\"mode\":\"singleplayer\",\"difficulty\":0,\"player\":\"true\",\"cells\":[{\"row\":0,\"col\":0,\"value\":\" X\"}]}}"
      slickFieldDAO.update(id, updatedField)
      slickFieldDAO.read(id) should be(updatedField)

      slickFieldDAO.delete(id)
      
      slickFieldDAO.read(id) should be("")
      
    }
  
  }
}
