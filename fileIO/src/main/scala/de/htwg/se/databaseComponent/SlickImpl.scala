import scala.io.Source
import play.api.libs.json._
import scala.concurrent.Await
import scala.concurrent.duration._
import de.htwg.se.databaseComponent.slickFieldDAO

object SlickImpl {

  // read latest FieldTable entry
  def load(): String = {
    val id = 1;
    slickFieldDAO.read(id)
  }
  def save(jsonField: String) = {
    slickFieldDAO.create(jsonField)
  }

}
