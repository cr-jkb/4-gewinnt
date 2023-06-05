import de.htwg.se.model.fieldComponent.fieldBaseImpl.Field
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

import de.htwg.se.fileIOComponent.fileIOJsonImpl.fileIOJsonImpl
import de.htwg.se.util.Util
import de.htwg.se.databaseComponent.MongodbImpl
import de.htwg.se.databaseComponent.SlickImpl

class fileIOTest extends AnyWordSpec {
  "FileIO" should {
    val field1 = new Field(1, 2, Stone.Empty)
    var field2 = new Field(1, 2, Stone.Empty)
    field2.setPlayer("false")
    field2 = field2.put(0, 0)

    "write to and read from MongoDB" in {
      MongodbImpl().save(Util.fieldToJson(field1))
      Util.jsonToField(MongodbImpl().load()) should be(
        field1
      )
    }
    "write to and read from Slick" in {
      SlickImpl().save(Util.fieldToJson(field1))
      Util.jsonToField(SlickImpl().load()) should be(
        field1
      )
    }
    "write to and read from JSON File" in {
      fileIOJsonImpl().save(Util.fieldToJson(field1))
      Util.jsonToField(fileIOJsonImpl().load()) should be(
        field1
      )
    }
  }
}
