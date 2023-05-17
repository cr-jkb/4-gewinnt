import scala.io.Source
import play.api.libs.json._
import scala.concurrent.Await
import scala.concurrent.duration._

object SlickImpl {
  
    def loadGame():JsValue = {
        Await.result(fieldDAO.read,15.seconds)
    }

}
