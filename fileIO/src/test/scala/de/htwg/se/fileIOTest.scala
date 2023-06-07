import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

import de.htwg.se.fileIOComponent.fileIOJsonImpl.fileIOJsonImpl
import de.htwg.se.databaseComponent.MongodbImpl
import de.htwg.se.databaseComponent.SlickImpl
import de.htwg.se.databaseComponent.mongoFieldDAO


class fileIOTest extends AnyWordSpec {
  val testField : String = "{\"field\":{\"mode\":\"multiplayer\",\"difficulty\":-1,\"player\":\"false\",\"cells\":[{\"row\":0,\"col\":0,\"value\":\" X\"}]}}"
  

  "FileIO" should {
    
    "write to and read from MongoDB" in {
      MongodbImpl.save(testField)
      MongodbImpl.load() should be(
        testField
      )
    }
    // "write to and read from Slick" in {
    //   SlickImpl().save(Util.fieldToJson(field1))
    //   Util.jsonToField(SlickImpl().load()) should be(
    //     field1
    //   )
    // }
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
      //mongoFieldDAO.read(id) should equal(null)
    }
  
  }
}
